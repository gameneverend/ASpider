package com.less.aspider.downloader;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Request;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.util.Singleton;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;


/**
 * @author deeper
 * @date 2017/12/17
 */

public class OkHttpDownloader implements Downloader {

    private ProxyProvider proxyProvider;

    static public OkHttpClient getOkhttpDefault() {
        return gDefault.get();
    }

    private static final Singleton<OkHttpClient> gDefault = new Singleton<OkHttpClient>() {

        @Override
        protected OkHttpClient create() {
            OkHttpClient okHttpClient = new OkHttpClient();
            return okHttpClient;
        }
    };

    @Override
    public void setProxyProvider(ProxyProvider proxyProvider){
        this.proxyProvider = proxyProvider;
    }

    @Override
    public Page download(Request request) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url(request.getUrl())
                .get();
        Page page = new Page();
        Call call = null;
        com.less.aspider.bean.Proxy proxyBean = null;
        if (proxyProvider != null && (proxyBean = proxyProvider.getProxy()) != null) {
            System.out.println("======> Request Proxy: " + proxyBean.getHost() + " : " + proxyBean.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyBean.getHost(), proxyBean.getPort()));
            call = getOkhttpDefault().newBuilder()
                    .proxy(proxy)
                    .build()
                    .newCall(builder.build());
        } else {
            System.out.println("=====> Request Nomal <=====");
            call = getOkhttpDefault().newCall(builder.build());
        }

        try {
            Response response = call.execute();
            page.setUrl(request.getUrl());
            page.setRawText(response.body().string());
            page.setDownloadSuccess(true);
            return page;
        } catch (IOException e) {
            page.setDownloadSuccess(false);
            e.printStackTrace();
        } finally {
            if (proxyProvider != null && proxyBean != null) {
                proxyProvider.returnProxy(proxyBean,page);
            }
        }
        return page;
    }
}
