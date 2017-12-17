package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;

/**
 * Created by deeper on 2017/12/17.
 */

public interface Scheduler {

    public void push(Request request);

    public Request poll();

}
