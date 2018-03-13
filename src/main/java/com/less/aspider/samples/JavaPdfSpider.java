package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.dao.DatabaseHelper;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.samples.bean.JavaPdf;
import com.less.aspider.scheduler.PriorityScheduler;
import com.less.aspider.util.RegexUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deeper on 2018/2/14.
 */

public class JavaPdfSpider extends DatabaseHelper {

    public static void main(String[] args) {
        String startUrl = "http://www.java1234.com";
        final String regex1 = "[http|https]+://pan.baidu.com/s/\\w+";
        final String regex2 = "[http|https]+://pan.baidu.com/share/link\\?shareid=\\d+\\&amp;uk=\\d+";
        final String regex3 = "<span style=\"color:#ff0000;\">(\\w+)</span>";
        final String regex4 = "<title>(.+)</title>";

        final JavaPdfSpider javaPdfSpider = new JavaPdfSpider();
        javaPdfSpider.createTable(JavaPdf.class);

        HttpConnDownloader httpConnDownloader = new HttpConnDownloader();
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "www.java1234.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        httpConnDownloader.setHeaders(headers);

        ASpider.create()
                .scheduler(new PriorityScheduler())
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        try {
                            String html = page.getRawText();
                            Document doc = Jsoup.
                            Elements elements = doc.select("a");
                            for (Element e : elements) {
                                String href = e.attr("abs:href");
                                System.out.println(href);
                            }

                            String panUrl = RegexUtils.get(regex1).selectSingle(html, 0);
                            if (panUrl.length() == 0) {
                                panUrl = RegexUtils.get(regex2).selectSingle(html, 0).replace("&amp;","&");
                            }
                            String title = RegexUtils.get(regex4).selectSingle(html, 1).replace("下载_Java知识分享网-免费Java资源下载","").trim();
                            String passwd = RegexUtils.get(regex3).selectSingle(html, 1);
                            if (!panUrl.isEmpty()) {
                                javaPdfSpider.insert(new JavaPdf(title, panUrl, passwd, page.getUrl()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .downloader(httpConnDownloader)
                .scheduler(new PriorityScheduler())
                .sleepTime(0)
                .retrySleepTime(0)
                .thread(50)
                .urls(startUrl)
                .run();
    }

    @Override
    public String dbname() {
        return "jpdf";
    }
}
