package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;

/**
 * Created by deeper on 2017/12/17.
 */

public class QueueScheduler implements Scheduler{

    @Override
    public void push(Request request) {

    }

    @Override
    public Request poll() {
        return null;
    }
}
