package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;
import com.less.aspider.bean.Request;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.pipeline.Pipeline;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;
import com.less.aspider.samples.bean.Ape;
import com.less.aspider.samples.db.FiveApeDao;
import com.less.aspider.scheduler.PriorityScheduler;
import com.less.aspider.util.L;
import com.less.aspider.util.RegexUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class FiveApeSpider {

    private static FiveApeDao fiveApeDao = new FiveApeDao();

    private static final String regex = "http://www.51ape.com/ape/(\\d+)\\.html";

    public static void main(String args[]) {
        fiveApeDao.createTable();

        // Proxy 代理设置
        final String path = System.getProperty("user.dir") + File.separator + "ASpidder" + File.separator + "src/main/java/proxy.txt";
        ProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy("113.209.100.170", 23128,"sy0bwptrqo","kb46o4cp0i"));
        Downloader downloader = new HttpConnDownloader();
        // headers 设置(具有时效性)
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "www.51ape.com");
        headers.put("Referer", "http://www.51ape.com/");
        headers.put("User-Agent", "Mozilla/5.0 (compatible; Baiduspider-render/2.0; +http://www.baidu.com/search/spider.html)");

        downloader.setHeaders(headers);
        // downloader.setProxyProvider(proxyProvider);

        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        if (!url.startsWith("http://www.51ape.com") || url.contains("php")) {
                            return;
                        }
                        if (RegexUtils.get(regex).matchers(url)) {
                            Document doc = Jsoup.parse(page.getRawText());
                            String name = doc.select("h1[class=yh mt_1 f_32]").get(0).text();
                            String album = doc.select("h3[class=c999 fl mt_05 f_12 n yh]").get(0).text().replace("·", "");
                            String kbps = doc.select("h3[class=c999 fl mt_05 f_12 n yh]").get(1).text().replace("·", "");
                            String size = doc.select("h3[class=c999 fl mt_05 f_12 n yh]").get(2).text().replace("·", "");
                            String pan_url = doc.select("h3[class=c999 fl mt_05 f_12 n yh] ~ a").attr("href");
                            String pan_pwd = doc.select("h3[class=c999 fl mt_05 f_12 n yh] ~ b").text().replace("提取密码：", "");
                            Ape ape = new Ape(name,album,kbps,size,pan_url,pan_pwd);
                            page.putField("ape",ape);
                        }

                        Document doc = Jsoup.parse(page.getRawText(), "http://www.51ape.com");
                        Elements elements = doc.select("a");
                        for (Element e : elements) {
                            String href = e.attr("abs:href");
                            page.addTargetRequest(new Request(href));
                        }
                    }
                })
                .thread(100)
                .downloader(downloader)
                .scheduler(new PriorityScheduler())
                .sleepTime(0)
                .retrySleepTime(0)
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(Map<String, Object> fields) {
                        for (Map.Entry<String, Object> entry : fields.entrySet()) {
                            Ape ape = (Ape) entry.getValue();
                            L.d(ape.toString());
                            fiveApeDao.save(ape);
                        }
                    }
                })
                .urls("http://www.51ape.com/")
                .run();
    }
}
