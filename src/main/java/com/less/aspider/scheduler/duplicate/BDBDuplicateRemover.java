package com.less.aspider.scheduler.duplicate;

import com.less.aspider.bean.Request;
import com.less.aspider.util.BDBHelper;

/**
 * @author deeper
 * @date 2017/12/22
 */

public class BDBDuplicateRemover implements DuplicateRemover {

    private BDBHelper bdbHelper;

    public BDBDuplicateRemover(BDBHelper bdbHelper) {
        this.bdbHelper = bdbHelper;
    }

    @Override
    public boolean isDuplicate(Request request) {
        if (bdbHelper.containsKey(request.getUrl())) {
            return true;
        } else {
            bdbHelper.put(request.getUrl(),true);
            return false;
        }
    }

    @Override
    public void resetDuplicateCheck() {
        bdbHelper.clearAll();
    }
}
