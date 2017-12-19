package com.less.aspider.downloader;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Request;
import com.less.aspider.proxy.ProxyProvider;

/**
 * Created by deeper on 2017/12/17.
 */

public interface Downloader {
    Page download(Request request);

    void setProxyProvider(ProxyProvider proxyProvider);
}
