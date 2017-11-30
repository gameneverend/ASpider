package com.less.aspider;

import com.less.aspider.util.CountableThreadPool;

public class App {
    public static void main(String args[]) {
        CountableThreadPool countableThreadPool = new CountableThreadPool(3);
        for(int i = 0;i < 10;i++) {
            countableThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("=========> " );
                }
            });
        }
    }
}
