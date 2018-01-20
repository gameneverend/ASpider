package com.less.aspider.proxy;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;
import com.less.aspider.util.L;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author deeper
 * @date 2017/12/19
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
        File file = new File(path);
        List<Proxy> proxies = readFile(file);
        return new SimpleProxyProvider(proxies);
    }

    public static SimpleProxyProvider from(File file) {
        List<Proxy> proxies = readFile(file);
        return new SimpleProxyProvider(proxies);
    }

    private static List<Proxy> readFile(File file) {
        List<Proxy> proxies = new ArrayList<>();
        try {
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
        return proxies;
    }

    @Override
    public void add(Proxy proxy) {
        proxies.add(proxy);
    }

    @Override
    public void remove(Proxy proxy) {
        proxies.remove(proxy);
    }

    public static SimpleProxyProvider from(Proxy... proxies) {
        List<Proxy> proxiesTemp = new ArrayList<Proxy>(proxies.length);
        for (Proxy proxy : proxies) {
            proxiesTemp.add(proxy);
        }
        return new SimpleProxyProvider(Collections.synchronizedList(proxiesTemp));
    }

    public synchronized void load(File file) {
        List<Proxy> proxies = readFile(file);
        for (Proxy proxy : proxies) {
            if (!this.proxies.contains(proxy)) {
                this.proxies.add(proxy);
            }
        }
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
                if (errorTiems > 50) {
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

    @Override
    public void onChanged(Object param) {
        L.d("EventBus => onChanged");
        File file = (File) param;
        load(file);
        try {
            // 清空代理文本,
            FileUtils.writeStringToFile(file,"","UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInvalidated() {

    }
}
