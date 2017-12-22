package com.less.aspider.scheduler;

import com.less.aspider.bean.Request;
import com.less.aspider.scheduler.duplicate.BDBDuplicateRemover;
import com.less.aspider.scheduler.duplicate.DuplicateRemover;
import com.less.aspider.util.BDBHelper;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BDB排重(海量数据排重,如: Google用于保存账号信息)
 * @author deeper
 * @date 2017/12/22
 */

public class BDBScheduler implements Scheduler {

    BDBHelper bdbHelper;
    private BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();
    private DuplicateRemover duplicatedRemover;

    public BDBScheduler() {
        String appPath = System.getProperty("user.dir") + File.separator + "bdb";
        bdbHelper = new BDBHelper(appPath);
        duplicatedRemover = new BDBDuplicateRemover(bdbHelper);
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
        bdbHelper.close();
    }

    private boolean shouldReserved(Request request) {
        return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
    }
}
