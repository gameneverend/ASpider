package com.less.aspider.util;

/**
 * Created by deeper on 2017/12/22.
 */

public class NumberUtils {

    public static int compareLong(long o1, long o2) {
        if (o1 < o2) {
            return -1;
        } else if (o1 == o2) {
            return 0;
        } else {
            return 1;
        }
    }
}
