package com.less.aspider.eventbus;

import com.less.aspider.bean.Request;
import com.less.aspider.http.HttpConnUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author deeper
 * @date 2018/1/17
 */

public class SimpleEventBus extends BaseEventBus {

    private static SimpleEventBus instance;

    private static ScheduledExecutorService executorService;

    private static HttpConnUtils httpConnUtils = new HttpConnUtils();

    public static SimpleEventBus create() {
        return new SimpleEventBus();
    }

    public static SimpleEventBus getInstance() {
        if (instance == null) {
            synchronized (SimpleEventBus.class) {
                if (instance == null) {
                    instance = new SimpleEventBus();
                }
            }
        }
        return instance;
    }

    @Override
    public void startWork(final String path, int period, boolean daemon) {
        // org.apache.commons.lang3.concurrent.BasicThreadFactory
        // initialDelay,period,TimeUnit.SECONDS); 从现在开始initialDelay 秒之后,每隔period 秒执行一次job,不建议用Timer.
        executorService = new ScheduledThreadPoolExecutor(1,
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
        },0, period, TimeUnit.SECONDS);
    }

    @Override
    public void startAPI(final String api, final String path, int period, boolean daemon) {
        executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(daemon).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] datas = httpConnUtils.sendRequest(new Request(api));
                    System.out.println(new String(datas));

                    File file = new File(path);
                    try {
                        FileUtils.writeByteArrayToFile(file, datas, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (file.exists()) {
                        notifyDataSetChanged(file);
                    } else {
                        System.err.println("temp proxy file not exsist!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0, period, TimeUnit.SECONDS);
    }
}
