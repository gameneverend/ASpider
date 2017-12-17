package com.less.aspider.scheduler.impl;

import com.less.aspider.bean.Request;
import com.less.aspider.scheduler.Scheduler;

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
