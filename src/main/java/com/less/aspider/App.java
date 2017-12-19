package com.less.aspider;

import com.less.aspider.bean.Page;
import com.less.aspider.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
public class App {

    public static void main(String args[]) {
        final String categoryRegex = "https://www.meitulu.com/t/[a-zA-Z]*/";
        String detailRegex = "https://mtl.ttsqgs.com/images/img/.*.jpg";

        final Pattern patternCategory = Pattern.compile(categoryRegex);
        final Pattern patternDetail = Pattern.compile(detailRegex);
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
                        Matcher matcherCategory = patternCategory.matcher(html);
                        List<String> urls = new ArrayList<String>();
                        while (matcherCategory.find()) {
                            String group = matcherCategory.group();
                            urls.add(group);
                        }
                        System.out.println(urls);
                        page.addTargetRequests(urls);

                        Matcher matcherDetail = patternDetail.matcher(html);
                        int i = 0;
                        while (matcherDetail.find()) {
                            String group = matcherDetail.group();
                            page.putField("name" + i, group);
                            i++;
                        }
                    }
                })
                .thread(5)
                .urls("https://www.meitulu.com/")
                .run();
    }
}
