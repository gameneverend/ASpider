package com.less.aspider.proxy;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;
import com.less.aspider.util.L;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author deeper
 * @date 2017/12/19
 */

public class SimpleProxyProvider implements ProxyProvider {

    private List<Proxy> proxies = new CopyOnWriteArrayList<>();
    private List<Proxy> errorProxies = new CopyOnWriteArrayList<>();
    private int maxErrorTimes = 10;
    private AtomicInteger pointer;
    private boolean loveLive = false;

    public SimpleProxyProvider() {
        pointer = new AtomicInteger(-1);
    }

    public SimpleProxyProvider maxErrorTimes(int maxErrorTimes) {
        this.maxErrorTimes = maxErrorTimes;
        return this;
    }

    public SimpleProxyProvider(List<Proxy> proxies) {
        this(proxies, new AtomicInteger(-1));
    }

    private SimpleProxyProvider(List<Proxy> proxies, AtomicInteger pointer) {
        this.proxies.addAll(proxies);
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
        return new SimpleProxyProvider(proxiesTemp);
    }

    public synchronized void load(File file) {
        List<Proxy> proxies = readFile(file);
        for (Proxy proxy : proxies) {
            if (!this.proxies.contains(proxy) && !errorProxies.contains(proxy)) {
                this.proxies.add(proxy);
            }
        }
    }

    @Override
    public ProxyProvider longLive() {
        this.loveLive = true;
        return this;
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
                int errorTimes = proxy.getErrorTimes();
                if (errorTimes > maxErrorTimes && !loveLive) {
                    errorProxies.add(proxy);
                    proxies.remove(proxy);
                } else {
                    proxy.setErrorTimes(errorTimes + 1);
                }
            }
        }
    }

    @Override
    public int length() {
        return proxies.size();
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
        File file = (File) param;
        load(file);
        L.d("EventBus => onChanged" + " proxies: " + proxies.size() + " errorProxies: " + errorProxies.size());
    }

    @Override
    public void onInvalidated() {

    }
}
