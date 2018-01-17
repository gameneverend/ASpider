package com.less.aspider.eventbus;

/**
 * Created by deeper on 2018/1/17.
 */

public class DataSetObservable extends Observable<DataSetObserver> {

    public void notifyChanged(Object param) {
        synchronized(mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged(param);
            }
        }
    }

    public void notifyInvalidated() {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onInvalidated();
            }
        }
    }
}