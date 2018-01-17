package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;
import com.less.aspider.bean.Request;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.SimpleProxyProvider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author deeper
 * @date 2018/1/16
 */

public class PostSpider {

    public static final String startUrl = "http://localhost/test/index.php";
    public static final String baseUrl = "http://localhost/test/";

    public static void main(String[] args) {
        Downloader downloader = new HttpConnDownloader();
        // fidder
        downloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",8888)));

        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String html = page.getRawText();
                        if (page.getUrl().equals(startUrl)) {
                            Document doc = Jsoup.parse(html);
                            String a_1 = doc.select("a").first().attr("href");
                            String a_2 = doc.select("a").eq(1).attr("href");
                            String url_form = baseUrl + a_1;
                            String url_json = baseUrl + a_2;
                            System.out.println(url_form);
                            System.out.println(url_json);

                            // Content-type: application/x-www-form-urlencoded
                            
                            Request request1 = new Request();
                            request1.setUrl(url_form);
                            request1.setMethod(Request.METHOD_POST);
                            request1.setCharset("UTF-8");
                            request1.setPostType(Request.POST_TYPE_FORM);
                            request1.addParameter("username", "xiaoming");
                            request1.addParameter("password", "1234");
                            request1.addParameter("age", 10);
                            page.addTargetRequest(request1);

                            Request request2 = new Request();
                            request2.setUrl(url_json);
                            request2.setMethod(Request.METHOD_POST);
                            request2.setCharset("UTF-8");
                            request2.setPostType(Request.POST_TYPE_JSON);
                            request2.addParameter("username", "xiaoming");
                            request2.addParameter("password", "1234");
                            request2.addParameter("age", 10);
                            page.addTargetRequest(request2);
                        }
                    }
                })
                .thread(10)
                .downloader(downloader)
                .sleepTime(0)
                .retrySleepTime(0)
                .urls(startUrl)
                .run();
    }
}
