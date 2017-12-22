package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;
import com.less.aspider.scheduler.duplicate.DuplicateRemover;
import com.less.aspider.scheduler.duplicate.HashSetDuplicateRemover;
import com.less.aspider.util.NumberUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by deeper on 2017/12/21.
 */

public class PriorityScheduler implements Scheduler {

    public static final int INITIAL_CAPACITY = 5;

    private DuplicateRemover duplicatedRemover = new HashSetDuplicateRemover();

    private BlockingQueue<Request> noPriorityQueue = new LinkedBlockingQueue<Request>();

    private PriorityBlockingQueue<Request> priorityQueuePlus = new PriorityBlockingQueue<Request>(INITIAL_CAPACITY, new Comparator<Request>() {
        @Override
        public int compare(Request o1, Request o2) {
            return -NumberUtils.compareLong(o1.getPriority(), o2.getPriority());
        }
    });

    private PriorityBlockingQueue<Request> priorityQueueMinus = new PriorityBlockingQueue<Request>(INITIAL_CAPACITY, new Comparator<Request>() {
        @Override
        public int compare(Request o1, Request o2) {
            return -NumberUtils.compareLong(o1.getPriority(), o2.getPriority());
        }
    });

    @Override
    public void push(Request request) {
        if (shouldReserved(request) || !duplicatedRemover.isDuplicate(request)) {
            if (request.getPriority() == 0) {
                noPriorityQueue.add(request);
            } else if (request.getPriority() > 0) {
                priorityQueuePlus.put(request);
            } else {
                priorityQueueMinus.put(request);
            }
        }
    }

    @Override
    public synchronized Request poll() {
        Request poll = priorityQueuePlus.poll();
        if (poll != null) {
            return poll;
        }
        poll = noPriorityQueue.poll();
        if (poll != null) {
            return poll;
        }
        return priorityQueueMinus.poll();
    }

    @Override
    public void close() throws IOException {

    }

    private boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
    }
}
