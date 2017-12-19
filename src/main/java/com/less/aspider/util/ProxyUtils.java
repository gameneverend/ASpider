package com.less.aspider.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Proxy验证
 * @author deeper
 * @date 2017/12/19
 */

public class ProxyUtils {

    public static boolean validateProxy(String host,int port) {
        Socket socket = null;
        try {
            socket = new Socket();
            InetSocketAddress endpointSocketAddr = new InetSocketAddress(host, port);
            socket.connect(endpointSocketAddr, 3000);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FAILRE - CAN not connect!  remote: " + host);
            return false;
        } finally {
                CloseUtils.closeQuietly(socket);
        }
    }
}