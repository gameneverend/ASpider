package com.less.aspider.http;

import com.google.gson.JsonObject;
import com.less.aspider.bean.Request;
import com.less.aspider.util.HttpsUtils;
import com.less.aspider.util.Singleton;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

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
            // ignore
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(sslParams.sSLSocketFactory);
            HttpsURLConnection.setDefaultHostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);

            return httpConnUtils;
        }
    };

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public byte[] sendRequest(Request request) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        appendHeaders(request,connection);

        connection.setDoInput(true);
        connection.setRequestMethod(request.getMethod());
        connection.setConnectTimeout(1000 * 5);
        connection.setReadTimeout(1000 * 5);

        if (request.getMethod().equals(Request.METHOD_POST)) {
            connection.setDoOutput(true);
            sendPostParameters(request,connection);
        }
        byte[] datas = readInputStream(connection.getInputStream());
        return datas;
    }

    public byte[] sendRequestByProxy(String url, String ip, int port) throws Exception {
        return sendRequestByProxy(new Request(url), new com.less.aspider.bean.Proxy(ip,port));
    }

    public byte[] sendRequestByProxy(Request request, final com.less.aspider.bean.Proxy proxyBean) throws Exception {
        final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyBean.getHost(), proxyBean.getPort()));
        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection(proxy);
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
        appendHeaders(request,connection);

        connection.setDoInput(true);
        connection.setRequestMethod(request.getMethod());
        connection.setConnectTimeout(1000 * 30);
        connection.setReadTimeout(1000 * 30);

        if (request.getMethod().equals(Request.METHOD_POST)) {
            connection.setDoOutput(true);
            sendPostParameters(request,connection);
        }

        byte[] datas = readInputStream(connection.getInputStream());
        return datas;
    }

    private void appendHeaders(Request request, HttpURLConnection connection) {
        if (null != headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (null != request.getHeaders()) {
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
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

    private void sendPostParameters(Request request,HttpURLConnection connection) throws IOException {
        StringBuffer params = new StringBuffer();
        OutputStream out = null;
        switch (request.getPostType()) {
            case Request.POST_TYPE_FORM:
                // default: connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                for (Map.Entry<String, Object> entry : request.getParameters().entrySet()) {
                    params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                out = connection.getOutputStream();
                out.write(params.toString().getBytes());
                break;
            case Request.POST_TYPE_JSON:
                connection.setRequestProperty("Content-Type","application/json; charset=utf-8");
                JsonObject jsonObject = new JsonObject();
                for (Map.Entry<String, Object> entry : request.getParameters().entrySet()) {
                    jsonObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
                }
                out = connection.getOutputStream();
                out.write(jsonObject.toString().getBytes());
                break;
            default:
                break;
        }
    }

    public void addHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}