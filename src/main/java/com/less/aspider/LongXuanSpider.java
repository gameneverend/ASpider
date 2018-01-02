package com.less.aspider;

import com.less.aspider.bean.Page;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.pipeline.ConsolePipeline;
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
    public static final String BASE_URL = "http://ilxdh.com/";
    public static void main(String args[]) {
		final String regex = "(http)+://[^\\s]+(com|cn|org|net)+";

        String path = System.getProperty("user.dir") + File.separator + "proxy.txt";
        ProxyProvider proxyProvider = SimpleProxyProvider.from(path);
        Downloader downloader = new HttpConnDownloader();
        downloader.setProxyProvider(proxyProvider);
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        if(RegexUtils.get(regex).matchers(url)){
                        	page.putField("url", url);
                        	List<String> list = RegexUtils.get(regex).selectList(page.getRawText(),0);
                        	page.addTargetRequests(list);
                        }
                    }
                })
                .thread(5)
                .sleepTime(0)
                .retrySleepTime(0)
                .downloader(downloader)
                .addPipeline(new ConsolePipeline())
                .urls("http://ilxdh.com")
                .run();
    }
}
