package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;
import com.less.aspider.util.RegexUtils;

import java.io.File;
import java.util.List;

/**
 * @author Administrator
 */
public class LongXuanSpider {
    public static final String startUrl = "http://www.wanandroid.com/";
    public static void main(String args[]) {
        final String regex = "(http)+://[^\\s|\\?|&|'|\"]+(com|cn|org|net)+?";

        String path = System.getProperty("user.dir") + File.separator + "ASpidder" + File.separator + "src/main/java/proxy.txt";
        ProxyProvider proxyProvider = SimpleProxyProvider.from(path);
        Downloader downloader = new HttpConnDownloader();
        downloader.setProxyProvider(proxyProvider);
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        if (url.equals(startUrl)) {
                            List<String> list = RegexUtils.get(regex).selectList(page.getRawText(), 0);
                            page.addTargetRequestsHasReferer(list,url);
                        } else if(RegexUtils.get(regex).matchers(url) && page.getRefererUrl().equals(startUrl)){
                            page.putField("url", url);
                            List<String> list = RegexUtils.get(regex).selectList(page.getRawText(),0);
                            page.addTargetRequestsHasReferer(list,url);
                        }
                    }
                })
                .thread(20)
                .sleepTime(0)
                .retrySleepTime(0)
                .downloader(downloader)
                .urlsHasReferer(startUrl,startUrl)
                .run();
    }
}
