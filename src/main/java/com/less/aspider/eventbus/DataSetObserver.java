package com.less.aspider.eventbus;

/**
 * Created by deeper on 2018/1/17.
 */

public interface DataSetObserver {

    void onChanged(Object object);

    void onInvalidated();
}
