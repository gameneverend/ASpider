package com.less.aspider.downloader;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;
import com.less.aspider.bean.Request;
import com.less.aspider.http.OkHttpUtils;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.util.L;

import java.util.Map;

/**
 * @author deeper
 * @date 2017/12/17
 */

public class OkHttpDownloader implements Downloader {

    private ProxyProvider proxyProvider;

    @Override
    public void setProxyProvider(ProxyProvider proxyProvider){
        this.proxyProvider = proxyProvider;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        if (null != headers) {
            OkHttpUtils.getDefault().setHeaders(headers);
        }
    }

    @Override
    public Page download(Request request) {
        Page page = new Page();
        Proxy proxyBean = null;
        byte[] bytes = null;
        if (proxyProvider != null && (proxyBean = proxyProvider.getProxy()) != null) {
            L.d("======> Request Proxy: " + request.getUrl() + " " + proxyBean.getHost() + " : " + proxyBean.getPort());
            bytes = OkHttpUtils.getDefault().getByProxy(request.getUrl(),proxyBean.getHost(),proxyBean.getPort());
        } else {
            L.d("=====> Request Nomal: " + request.getUrl() + " <=====");
            bytes = OkHttpUtils.getDefault().get(request.getUrl());
        }
        page.setUrl(request.getUrl());
        page.setRefererUrl(request.getRefererUrl());
        page.setOriginRequest(request);

        if (null != bytes) {
            page.setRawText(new String(bytes));
            page.setDownloadSuccess(true);
        } else {
            page.setRawText(null);
            page.setDownloadSuccess(false);
        }
        if (proxyProvider != null && proxyBean != null) {
            proxyProvider.returnProxy(proxyBean, page);
        }
        return page;
    }
}
