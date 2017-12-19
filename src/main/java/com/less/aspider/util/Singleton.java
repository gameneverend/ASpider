package com.less.aspider.util;

/**
 * Created by deeper on 2017/12/19.
 */

public abstract class Singleton<T> {
    private T mInstance;
    protected abstract T create();
    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}