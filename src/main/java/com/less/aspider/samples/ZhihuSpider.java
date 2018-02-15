package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.scheduler.BDBScheduler;

/**
 * Created by deeper on 2018/2/14.
 */

public class ZhihuSpider {

    public static void main(String[] args) {
        // set GLOBAL innodb_flush_log_at_trx_commit = 0;
        ASpider.create()
                .sleepTime(0)
                .retrySleepTime(0)
                .thread(2)
                .scheduler(new BDBScheduler())
                .run();
    }
}
