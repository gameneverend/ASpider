package com.less.aspider;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Request;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.pipeline.ConsolePipeline;
import com.less.aspider.pipeline.Pipeline;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.scheduler.QueueScheduler;
import com.less.aspider.scheduler.Scheduler;
import com.less.aspider.util.CloseUtils;
import com.less.aspider.util.CountableThreadPool;
import com.less.aspider.util.L;
import com.less.aspider.util.SerializationUtil;
import com.less.aspider.util.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by deeper on 2017/11/28.
 */

public class ASpider implements Runnable {

    /** 失败重试次数 */
    private int errorRetryTimes = 3;

    private boolean errorReturn;

    private Downloader downloader;

    private List<Pipeline> pipelines = new ArrayList<>();

    private PageProcessor pageProcessor;

    private Scheduler scheduler = new QueueScheduler();

    private AtomicInteger stat = new AtomicInteger(STAT_INIT);

    private boolean exitWhenComplete = true;

    private final static int STAT_INIT = 0;

    private final static int STAT_RUNNING = 1;

    private final static int STAT_STOPPED = 2;

    private CountableThreadPool threadPool = new CountableThreadPool(3);

    private int threadNum = 1;

    private ReentrantLock newUrlLock = new ReentrantLock();

    private Condition newUrlCondition = newUrlLock.newCondition();

    private final AtomicLong pageCount = new AtomicLong(0);

    private ExecutorService executorService;

    private int retrySleepTime = 1000;

    private int sleepTime = 5000;

    public static ASpider create() {
        ASpider aSpider = new ASpider();
        return aSpider;
    }

    public ASpider sleepTime(int sleepTime){
        this.sleepTime = sleepTime;
        return this;
    }

    public ASpider retrySleepTime(int retrySleepTime) {
        this.retrySleepTime = retrySleepTime;
        return this;
    }

    public ASpider pageProcessor(PageProcessor pageProcessor){
        this.pageProcessor = pageProcessor;
        return this;
    }

    public ASpider addPipeline(Pipeline pipeline) {
        this.pipelines.add(pipeline);
        return this;
    }

    public ASpider downloader(Downloader downloader) {
        this.downloader = downloader;
        return this;
    }

    public ASpider scheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
        return this;
    }

    public ASpider executorService(ExecutorService executorService){
        this.executorService = executorService;
        return this;
    }

    public ASpider urlsHasReferer(String startUrl,String refererUrl) {
        addRequest(new Request(startUrl,refererUrl));
        signalNewUrl();
        return this;
    }

    public ASpider urlsHasReferer(String[] urls,String refererUrl) {
        for (String url : urls) {
            addRequest(new Request(url,refererUrl));
        }
        signalNewUrl();
        return this;
    }

    public ASpider urls(String... urls) {
        for (String url : urls) {
            addRequest(new Request(url));
        }
        signalNewUrl();
        return this;
    }

    private void addRequest(Request request) {
        scheduler.push(request);
    }

    private void waitNewUrl() {
        newUrlLock.lock();
        try {
            //double check
            if (threadPool.getThreadAlive() == 0 && exitWhenComplete) {
                return;
            }
            newUrlCondition.await(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.err.println("waitNewUrl - interrupted, error {}" + e.toString());
        } finally {
            newUrlLock.unlock();
        }
    }

    private void signalNewUrl() {
        try {
            newUrlLock.lock();
            newUrlCondition.signalAll();
        } finally {
            newUrlLock.unlock();
        }
    }

    @Override
    public void run() {
        checkRunningStat();
        initComponent();
        while (!Thread.currentThread().isInterrupted() && stat.get() == STAT_RUNNING) {
            final Request request = scheduler.poll();
            if (request == null) {
                if (threadPool.getThreadAlive() == 0 && exitWhenComplete) {
                    break;
                }
                waitNewUrl();
            } else {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            processRequest(request);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            pageCount.incrementAndGet();
                            signalNewUrl();
                        }
                    }
                });
            }
        }
        stat.set(STAT_STOPPED);
        // release some resources
        CloseUtils.closeQuietly(scheduler);
    }

    private void processRequest(Request request) {
        Page page = downloader.download(request);
        if (page.isDownloadSuccess() || errorReturn){
            onDownloadSuccess(page);
        } else {
            onDownloaderFail(request);
        }
    }

    private void onDownloadSuccess(Page page) {
        pageProcessor.process(page);
        for (Request request : page.getTargetRequests()) {
            addRequest(request);
        }
        if (!page.isSkip()) {
            for (Pipeline pipeline : pipelines) {
                if (page.getFields() != null) {
                    pipeline.process(page.getFields());
                }
            }
        }
        ThreadUtils.sleep(sleepTime);
    }

    private void onDownloaderFail(Request request) {
        if (errorRetryTimes == 0) {
            ThreadUtils.sleep(sleepTime);
        } else {
            Object cycleTriedTimesObject = request.getExtra(Request.CYCLE_TRIED_TIMES);
            // 首次重试请求
            if (cycleTriedTimesObject == null) {
                addRequest(SerializationUtil.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, 1));
            } else {
                int cycleTriedTimes = (Integer) cycleTriedTimesObject;
                cycleTriedTimes++;
                if (cycleTriedTimes < errorRetryTimes) {
                    addRequest(SerializationUtil.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES, cycleTriedTimes));
                }
            }
            ThreadUtils.sleep(retrySleepTime);
        }
    }

    private void initComponent() {
        if (downloader == null) {
            this.downloader = new HttpConnDownloader();
        }
        if (pipelines.isEmpty()) {
            pipelines.add(new ConsolePipeline());
        }
        if (threadPool == null || threadPool.isShutdown()) {
            if (executorService != null) {
                threadPool = new CountableThreadPool(threadNum, executorService);
            } else {
                threadPool = new CountableThreadPool(threadNum);
            }
        }
    }

    public void runAsync() {
        Thread thread = new Thread(this);
        thread.setDaemon(false);
        thread.start();
    }

    private void checkRunningStat() {
        while (true) {
            int statNow = stat.get();
            if (statNow == STAT_RUNNING) {
                throw new IllegalStateException("Spider is already running!");
            }
            if (stat.compareAndSet(statNow, STAT_RUNNING)) {
                break;
            }
        }
    }

    public ASpider thread(int threadNum){
        this.threadNum = threadNum;
        if (threadNum <= 0) {
            throw new IllegalArgumentException("threadNum should be more than one!");
        }
        return this;
    }

    public void stop() {
        if (stat.compareAndSet(STAT_RUNNING,STAT_STOPPED)) {
            L.d("Spider stop success!");
        } else {
            L.d("Spider stop fail!");
        }
    }

    public int getThreadAlive() {
        if (threadPool == null) {
            return 0;
        }
        return threadPool.getThreadAlive();
    }

    public ASpider errorReturn(boolean errorReturn) {
        this.errorReturn = errorReturn;
        return this;
    }
}
