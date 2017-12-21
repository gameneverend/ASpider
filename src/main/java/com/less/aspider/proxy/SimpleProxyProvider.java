package com.less.aspider.proxy;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by deeper on 2017/12/19.
 */

public class SimpleProxyProvider implements ProxyProvider {
    private List<Proxy> proxies;
    private AtomicInteger pointer;

    public SimpleProxyProvider(List<Proxy> proxies) {
        this(proxies, new AtomicInteger(-1));
    }

    private SimpleProxyProvider(List<Proxy> proxies, AtomicInteger pointer) {
        this.proxies = proxies;
        this.pointer = pointer;
    }

    public static SimpleProxyProvider from(String path) {
        List<Proxy> proxies = new ArrayList<>();
        try {
            File file = new File(path);
            List<String> lines = FileUtils.readLines(file,"UTF-8");
            for (String line : lines) {
                try {
                    String host = line.split(":")[0];
                    String port = line.split(":")[1];
                    Proxy proxy = new Proxy(host,Integer.parseInt(port));
                    proxies.add(proxy);
                } catch (Exception ignore) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SimpleProxyProvider(proxies);
    }

    public static SimpleProxyProvider from(Proxy... proxies) {
        List<Proxy> proxiesTemp = new ArrayList<Proxy>(proxies.length);
        for (Proxy proxy : proxies) {
            proxiesTemp.add(proxy);
        }
        return new SimpleProxyProvider(Collections.unmodifiableList(proxiesTemp));
    }

    @Override
    public Proxy getProxy() {
        if (proxies.size() == 0) {
            return null;
        }
        return proxies.get(loop());
    }

    @Override
    public void returnProxy(Proxy proxy, Page page) {
        if (!page.isDownloadSuccess()) {
            synchronized (SimpleProxyProvider.class) {
                int errorTiems = proxy.getErrorTimes();
                if (errorTiems > 3) {
                    proxies.remove(proxy);
                } else {
                    proxy.setErrorTimes(errorTiems + 1);
                }
            }
        }
    }

    private int loop() {
        int size = proxies.size();
        if (size == 0) {
            return 0;
        }

        int p = pointer.incrementAndGet();
        if (p < size) {
            return p;
        }
        while (!pointer.compareAndSet(p, p % size)) {
            p = pointer.get();
        }
        return p % size;
    }
}
