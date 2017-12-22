package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;
import com.less.aspider.scheduler.duplicate.DuplicateRemover;
import com.less.aspider.scheduler.duplicate.HashSetDuplicateRemover;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by deeper on 2017/12/17.
 */

public class QueueScheduler implements Scheduler {

    private BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();
    private DuplicateRemover duplicatedRemover = new HashSetDuplicateRemover();

    public void setDuplicateRemover(DuplicateRemover duplicatedRemover) {
        this.duplicatedRemover = duplicatedRemover;
    }

    @Override
    public void push(Request request) {
        if (shouldReserved(request) || !duplicatedRemover.isDuplicate(request)) {
            queue.add(request);
        }
    }

    @Override
    public Request poll() {
        return queue.poll();
    }

    @Override
    public void close() throws IOException {
        // nothing to do
    }

    /**
     * 是否重复供应
     * 首次请求返回false,失败重试时返回true
     * @param request
     * @return
     */
    private boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
    }
}
