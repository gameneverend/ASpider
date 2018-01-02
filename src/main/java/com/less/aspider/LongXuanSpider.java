package com.less.aspider;

import com.less.aspider.bean.Page;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.pipeline.Pipeline;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;
import com.less.aspider.util.RegexUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class LongXuanSpider {
    public static final String BASE_URL = "http://ilxdh.com/";
    public static void main(String args[]) {
        final String regex = "(http)+://[^\\s|?|&]+(com|cn|org|net)+?";

        String path = System.getProperty("user.dir") + File.separator + "proxy.txt";
        ProxyProvider proxyProvider = SimpleProxyProvider.from(path);
        Downloader downloader = new HttpConnDownloader();
        // downloader.setProxyProvider(proxyProvider);
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        if(RegexUtils.get(regex).matchers(url) && page.getRefererUrl().equals("http://ilxdh.com")){
                            page.putField("url", url);
                            List<String> list = RegexUtils.get(regex).selectList(page.getRawText(),0);
                            page.addTargetRequests(url,list);
                        }
                    }
                })
                .thread(100)
                .sleepTime(0)
                .retrySleepTime(0)
                .downloader(downloader)
                .addPipeline(new Pipeline() {

                    @Override
                    public void process(Map<String, Object> map) {
                        System.err.println("url: " + map.get("url"));
                    }
                })
                .urls("http://ilxdh.com","http://ilxdh.com")
                .run();
    }
}
