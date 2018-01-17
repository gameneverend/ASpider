package com.less.aspider.eventbus;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author deeper
 * @date 2018/1/17
 */

public class SimpleEventBus extends BaseEventBus {

    public static SimpleEventBus create() {
        return new SimpleEventBus();
    }

    @Override
    public void startWork(final String path, boolean daemon) {
        // org.apache.commons.lang3.concurrent.BasicThreadFactory
        // initialDelay,period,TimeUnit.SECONDS); 从现在开始initialDelay 秒之后,每隔period 秒执行一次job,不建议用Timer.
        final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(daemon).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                File file = new File(path);
                if (file.exists()) {
                    notifyDataSetChanged(file);
                } else {
                    System.err.println("temp proxy file not exsist!");
                }
            }
        },1, 10, TimeUnit.SECONDS);
    }
}
