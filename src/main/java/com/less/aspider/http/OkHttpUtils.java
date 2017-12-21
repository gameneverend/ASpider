package com.less.aspider.http;

import com.less.aspider.util.Singleton;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by deeper on 2017/12/21.
 */

public class OkHttpUtils {
    private OkHttpClient okHttp;

    static public OkHttpUtils getDefault() {
        return gDefault.get();
    }

    private static final Singleton<OkHttpUtils> gDefault = new Singleton<OkHttpUtils>() {

        @Override
        protected OkHttpUtils create() {
            OkHttpUtils okHttpUtils = new OkHttpUtils();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            okHttpUtils.setOkHttp(builder.build());
            return okHttpUtils;
        }
    };


    public byte[] get(String url) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        try {
            Response response = okHttp.newCall(builder.build()).execute();
            byte[] bytes = response.body().bytes();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getByProxy(String url,String host,int port) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host,port));
        Call call = okHttp.newBuilder()
                .proxy(proxy)
                .build()
                .newCall(builder.build());
        try {
            Response response = call.execute();
            byte[] bytes = response.body().bytes();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setOkHttp(OkHttpClient okHttp) {
        this.okHttp = okHttp;
    }
}
