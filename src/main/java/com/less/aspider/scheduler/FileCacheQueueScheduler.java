package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;
import com.less.aspider.scheduler.duplicate.DuplicateRemover;
import com.less.aspider.scheduler.duplicate.HashSetDuplicateRemover;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Scheduler(状态保存,定时保存状态,初始化读取到内存中,仍然属于内存排重方式)
 * @author deeper
 * @date 2017/12/22
 */

public class FileCacheQueueScheduler implements Scheduler {

    private DuplicateRemover duplicatedRemover = new HashSetDuplicateRemover();

    private String filePath = System.getProperty("java.io.tmpdir");

    private String taskId = UUID.randomUUID().toString();

    private String fileUrlAllName = ".urls.txt";

    private String fileCursor = ".cursor.txt";

    private PrintWriter fileUrlWriter;

    private PrintWriter fileCursorWriter;

    private AtomicInteger cursor = new AtomicInteger();

    private AtomicBoolean inited = new AtomicBoolean(false);

    private BlockingQueue<Request> queue;

    private Set<String> urls;

    private ScheduledExecutorService flushThreadPool;

    public FileCacheQueueScheduler(String filePath,String taskId) {
        if (!filePath.endsWith("/") && !filePath.endsWith("\\")) {
            filePath += "/";
        }
        this.filePath = filePath;
        this.taskId = taskId;
        initDuplicateRemover();
    }

    private void flush() {
        fileUrlWriter.flush();
        fileCursorWriter.flush();
    }

    private void init() {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        readFile();
        initWriter();
        initFlushThread();
        inited.set(true);
        System.out.println("init cache scheduler success");
    }

    public void setDuplicateRemover(DuplicateRemover duplicatedRemover) {
        this.duplicatedRemover = duplicatedRemover;
    }

    private void initDuplicateRemover() {
        setDuplicateRemover(
                new DuplicateRemover() {
                    @Override
                    public boolean isDuplicate(Request request) {
                        if (!inited.get()) {
                            init();
                        }
                        return !urls.add(request.getUrl());
                    }

                    @Override
                    public void resetDuplicateCheck() {
                        urls.clear();
                    }
                });
    }

    private void initFlushThread() {
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1，取代Timer的。
        flushThreadPool = Executors.newScheduledThreadPool(1);
        flushThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                flush();
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

    private void initWriter() {
        try {
            // 支持追加
            fileUrlWriter = new PrintWriter(new FileWriter(getFileName(fileUrlAllName), true));
            // 不支持追加，因为只需保存一个数字即可
            fileCursorWriter = new PrintWriter(new FileWriter(getFileName(fileCursor), false));
        } catch (IOException e) {
            throw new RuntimeException("init cache scheduler error", e);
        }
    }

    private void readFile() {
        try {
            queue = new LinkedBlockingQueue<Request>();
            urls = new LinkedHashSet<String>();
            readCursorFile();
            readUrlFile();
            // initDuplicateRemover();
        } catch (FileNotFoundException e) {
            //init
            System.out.println("init cache file " + getFileName(fileUrlAllName));
        } catch (IOException e) {
            System.err.println("init file error" + e.getMessage());
        }
    }


    /**
     * 读取cursor的值
     */
    private void readCursorFile() throws IOException {
        BufferedReader fileCursorReader = null;
        try {
            fileCursorReader = new BufferedReader(new FileReader(getFileName(fileCursor)));
            String line;
            //read the last number
            while ((line = fileCursorReader.readLine()) != null) {
                cursor = new AtomicInteger(Integer.parseInt(line));
            }
        } finally {
            if (fileCursorReader != null) {
                IOUtils.closeQuietly(fileCursorReader);
            }
        }
    }

    /**
     * 从文件中读取urls到 任务队列和总排重队列中 内存中
     * @throws IOException
     */
    private void readUrlFile() throws IOException {
        String line;
        BufferedReader fileUrlReader = null;
        try {
            fileUrlReader = new BufferedReader(new FileReader(getFileName(fileUrlAllName)));
            int lineReaded = 0;
            while ((line = fileUrlReader.readLine()) != null) {
                urls.add(line.trim());
                lineReaded++;
                if (lineReaded > cursor.get()) {
                    queue.add(new Request(line));
                }
            }
        } finally {
            if (fileUrlReader != null) {
                IOUtils.closeQuietly(fileUrlReader);
            }
        }
    }

    /**
     * Spider中的close方法最终调用(当spider停止的时候)
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        flushThreadPool.shutdown();
        fileUrlWriter.close();
        fileCursorWriter.close();
    }

    private String getFileName(String filename) {
        return filePath + taskId + filename;
    }

    @Override
    public synchronized Request poll() {
        if (!inited.get()) {
            init();
        }
        fileCursorWriter.println(cursor.incrementAndGet());
        return queue.poll();
    }

    @Override
    public void push(Request request) {
        if (shouldReserved(request) || !duplicatedRemover.isDuplicate(request)) {
            if (!inited.get()) {
                init();
            }
            queue.add(request);
            fileUrlWriter.println(request.getUrl());
        }
    }

    private boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
    }
}

