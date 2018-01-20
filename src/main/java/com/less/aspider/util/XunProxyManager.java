package com.less.aspider.util;

import com.less.aspider.http.HttpConnUtils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by deeper on 2018/1/20.
 */

public class XunProxyManager {

    public static final String HEADER_PROXY_AUTH = "Proxy-Authorization";

    public static final String IP = "forward.xdaili.cn";

    public static final int PORT = 80;


    public static void main(String[] args) {
        String authHeader = createProxyAuthorization("ZF20181206870tBMzFp","5268960af3fb4b1cb572dda081e829f1");
        HttpConnUtils.getDefault().addHeader(HEADER_PROXY_AUTH, authHeader);

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            });
        }
    }

    private static void test() {
        String url ="http://1212.ip138.com/ic.asp";
        try {
            byte[] bytes = HttpConnUtils.getDefault().sendRequestByProxy(url, XunProxyManager.IP, XunProxyManager.PORT);
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String createProxyAuthorization(String orderId, String secret) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        String signuareText = String.format("orderno=%s,secret=%s,timestamp=%d", orderId, secret, timestamp);
        String sign = DigestUtils.md5Hex(signuareText).toUpperCase();

        String authHeader = String.format("sign=%s&orderno=%s&timestamp=%d", sign, orderId, timestamp);
        return authHeader;
    }
}
