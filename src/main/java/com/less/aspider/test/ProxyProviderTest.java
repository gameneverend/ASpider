package com.less.aspider.test;

import com.less.aspider.bean.Proxy;
import com.less.aspider.eventbus.BaseEventBus;
import com.less.aspider.eventbus.SimpleEventBus;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;

/**
 * Created by deeper on 2018/1/16.
 */

public class ProxyProviderTest {

    public static void main(String[] args) {
        BaseEventBus eventBus = SimpleEventBus.create();

        ProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy("xx", 22));
        eventBus.registerDataSetObserver(proxyProvider);
        eventBus.startWork("F:\\temp.txt",false);
    }
}
