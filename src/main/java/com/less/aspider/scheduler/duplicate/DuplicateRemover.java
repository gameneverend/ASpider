package com.less.aspider.scheduler.duplicate;

import com.less.aspider.bean.Request;

/**
 * Created by deeper on 2017/12/19.
 */

public interface DuplicateRemover {

    boolean isDuplicate(Request request);

    void resetDuplicateCheck();
}
