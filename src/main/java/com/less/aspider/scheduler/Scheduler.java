package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;

import java.io.Closeable;

/**
 * Created by deeper on 2017/12/17.
 */

public interface Scheduler extends Closeable {

    public void push(Request request);

    public Request poll();

}
