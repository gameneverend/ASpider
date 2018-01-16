package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.processor.PageProcessor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import us.codecraft.xsoup.Xsoup;

/**
 * 说明: 最简单的xpath demo,作者开放了api,抓取无意义,仅为测试!
 *
 * @author deeper
 * @date 2018/1/16
 *
 */

public class WanAndroidSpider {

    public static final String startUrl = "http://www.wanandroid.com/";

    public static void main(String[] args) {
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        Document document = Jsoup.parse(page.getRawText());
                        // 只获取xpath的第一个结果
                        String first = Xsoup.compile("//ul/li/div/div[2]/p/a").evaluate(document).get();
                        // 获取xpath集合
                        List<String> list = Xsoup.compile("//ul/li/div/div[2]/p/a").evaluate(document).list();
                        for (String result : list) {
                            System.out.println(result);
                        }
                        // 获取xpath element
                        Elements elements = Xsoup.compile("//ul/li/div/div[2]/p/a").evaluate(document).getElements();
                        for (Element element : elements) {
                            System.out.println(element.attr("abs:href"));
                        }
                    }
                })
                // > 注意: 建议大家手下留情,不要对该网站有过大的压力,运营网站不容易.
                .thread(3)
                .sleepTime(3000)
                .retrySleepTime(1000)
                .urls(startUrl)
                .run();
    }

}
