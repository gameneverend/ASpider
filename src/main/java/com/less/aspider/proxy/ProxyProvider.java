package com.less.aspider.proxy;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;

/**
 * Created by deeper on 2017/12/19.
 */

public interface ProxyProvider {
    /**
     * 从代理池获取一个代理
     * @return
     */
    Proxy getProxy();

    /**
     * 添加一个代理
     * @param proxy
     */
    void add(Proxy proxy);

    /**
     * 移除一个代理
     * @param proxy
     */
    void remove(Proxy proxy);

    /**
     * 该方法在okhttp使用代理下载完调用,代理池可以据此做些逻辑处理
     * @param proxy
     * @param page
     */
    void returnProxy(Proxy proxy, Page page);
}
