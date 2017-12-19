package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;

import java.io.IOException;

/**
 * Created by deeper on 2017/12/17.
 */

public class QueueScheduler implements Scheduler {

    @Override
    public void push(Request request) {

    }

    @Override
    public Request poll() {
        return null;
    }

    @Override
    public void close() throws IOException {
        // nothing to do
    }
}
