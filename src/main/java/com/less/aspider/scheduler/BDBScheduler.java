package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;
import com.less.aspider.scheduler.duplicate.BDBDuplicateRemover;
import com.less.aspider.scheduler.duplicate.DuplicateRemover;
import com.less.aspider.util.BDBHelper;
import com.less.aspider.util.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * BDB排重(海量数据排重,如: Google用于保存账号信息)
 * @author deeper
 * @date 2017/12/22
 */

public class BDBScheduler implements Scheduler {
    public static final int INITIAL_CAPACITY = 5;

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

    private BDBHelper bdbHelper;

    private DuplicateRemover duplicatedRemover;

    public BDBScheduler() {
        String appPath = System.getProperty("user.dir") + File.separator + "bdb";
        bdbHelper = new BDBHelper(appPath);
        duplicatedRemover = new BDBDuplicateRemover(bdbHelper);
    }

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
    public Request poll() {
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
        bdbHelper.close();
    }

    private boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
    }
}
