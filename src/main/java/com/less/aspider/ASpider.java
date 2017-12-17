package com.less.aspider;

import com.less.aspider.bean.Request;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.OkHttpDownloader;
import com.less.aspider.pipeline.ConsolePipeline;
import com.less.aspider.pipeline.Pipeline;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.scheduler.QueueScheduler;
import com.less.aspider.scheduler.Scheduler;
import com.less.aspider.util.CountableThreadPool;

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

    public static ASpider create() {
        ASpider aSpider = new ASpider();
        return aSpider;
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

    public ASpider urls(String... urls) {
        for (String url : urls) {
            addRequest(new Request(url));
        }
        signalNewUrl();
        return this;
    }

    private void addRequest(Request request) {
        System.out.println("fucking");
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
    }

    private void processRequest(Request request) {
        Page page = downloader.download(request, this);
        if (page.isDownloadSuccess()){
            onDownloadSuccess(request, page);
        } else {
            onDownloaderFail(request);
        }
    }

    private void initComponent() {
        if (downloader == null) {
            this.downloader = new OkHttpDownloader();
        }
        if (pipelines.isEmpty()) {
            pipelines.add(new ConsolePipeline());
        }
        if (threadPool == null || threadPool.isShutdown()) {
            if (executorService != null && !executorService.isShutdown()) {
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
}
