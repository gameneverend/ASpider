package com.less.aspider;

import com.less.aspider.bean.Page;
import com.less.aspider.processor.PageProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
public class App {

    public static void main(String args[]) {
        String categoryRegex = "[a-zA-z]+://[^\\s]*";
        String articleRegex = "//upload.jianshu.io/[^\\s]*";

        final Pattern pattern = Pattern.compile(articleRegex);
        /*
         * Proxy 代理设置
         * ProxyProvider proxyProvider = SimpleProxyProvider.from();
         * Downloader downloader = new OkHttpDownloader();
         * downloader.setProxyProvider(proxyProvider);
         */
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String html = page.getRawText();
                        Matcher matcher = pattern.matcher(html);
                        int i = 0;
                        while (matcher.find()) {
                            String group = matcher.group();
                            page.putField("name" + i, group);
                            i++;
                        }
                    }
                })
                .thread(3)
                .urls("http://www.jianshu.com/u/79a88a044955")
                .run();
    }
}
