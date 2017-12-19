package com.less.aspider.util;

import java.io.Closeable;

/**
 * Created by deeper on 2017/12/19.
 */

public class CloseUtils {
    public static void closeQuietly(Closeable ... closeables){
        try {
            for (Closeable closeable : closeables) {
                if (null != closeable) {
                    closeable.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
