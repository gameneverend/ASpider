package com.less.aspider.util;

/**
 * Created by deeper on 2017/12/19.
 */

public class ThreadUtils {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
