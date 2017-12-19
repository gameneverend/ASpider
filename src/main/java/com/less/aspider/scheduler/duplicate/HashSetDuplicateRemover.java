package com.less.aspider.scheduler.duplicate;

import com.less.aspider.bean.Request;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by deeper on 2017/12/19.
 */

public class HashSetDuplicateRemover implements DuplicateRemover {
    private Set<String> urls = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    @Override
    public boolean isDuplicate(Request request) {
        return !urls.add(request.getUrl());
    }

    @Override
    public void resetDuplicateCheck() {
        urls.clear();
    }
}
