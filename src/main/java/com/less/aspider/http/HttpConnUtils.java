package com.less.aspider.http;

import com.less.aspider.util.Singleton;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;

/**
 * Created by deeper on 2017/12/20.
 */

public class HttpConnUtils {

    static public HttpConnUtils getDefault() {
        return gDefault.get();
    }

    private Map<String,String> headers = null;

    private static final Singleton<HttpConnUtils> gDefault = new Singleton<HttpConnUtils>() {

        @Override
        protected HttpConnUtils create() {
            HttpConnUtils httpConnUtils = new HttpConnUtils();
            return httpConnUtils;
        }
    };

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public byte[] sendRequest(String url){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            if (null != headers) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(),entry.getValue());
                }
            }
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000 * 30);
            connection.setReadTimeout(1000 * 30);
            byte[] datas = readInputStream(connection.getInputStream());
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] datas = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return datas;
    }

    public byte[] sendRequestByProxy(String url, final com.less.aspider.bean.Proxy proxyBean) throws Exception {
        final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyBean.getHost(), proxyBean.getPort()));
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(proxy);
        if (null != proxyBean.getUsername() && !"".equals(proxyBean.getUsername())) {
            // 方式一: 但是针对https站点并未生效
            String value = proxyBean.getUsername() + ":" + proxyBean.getPassword();
            connection.setRequestProperty("Proxy-Authorization", "Basic " + Base64.encodeBase64String(value.getBytes()));

            // 方式二: 生效
            Authenticator authenticator = new Authenticator() {

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(proxyBean.getUsername(), proxyBean.getPassword().toCharArray());
                }
            };
            Authenticator.setDefault(authenticator);
        }
        connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        if (null != headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(),entry.getValue());
            }
        }
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(1000 * 30);
        connection.setReadTimeout(1000 * 30);
        byte[] datas = readInputStream(connection.getInputStream());
        return datas;
    }
}