package com.less.aspider.eventbus;

/**
 * Created by deeper on 2018/1/15.
 */

public abstract class BaseEventBus {

    private DataSetObservable mObservable = new DataSetObservable();

    public void notifyDataSetChanged(Object param) {
        mObservable.notifyChanged(param);
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        mObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mObservable.unregisterObserver(observer);
    }

    public abstract void startWork(String path, boolean daemon);
}
