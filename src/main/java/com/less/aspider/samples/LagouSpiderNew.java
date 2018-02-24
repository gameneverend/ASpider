package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;
import com.less.aspider.dao.DatabaseHelper;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;
import com.less.aspider.samples.bean.LagouDB;
import com.less.aspider.util.XunProxyManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import us.codecraft.xsoup.Xsoup;

import static com.less.aspider.util.XunProxyManager.createProxyAuthorization;

/**
 * Created by deeper on 2018/2/6.
 */

public class LagouSpiderNew extends DatabaseHelper {

    static Map<String, LagouDB> cache = new ConcurrentHashMap<>(10000);

    public static void main(String args[]) {
        final LagouSpiderNew lagouSpiderNew = new LagouSpiderNew();
        lagouSpiderNew.createTable(LagouDB.class);

        // List<LagouDB> list = lagouSpiderNew.queryListByPage("select * from lagou", 1, 30000, "id", LagouDB.class);
        List<LagouDB> list = lagouSpiderNew.queryList("select * from lagou", LagouDB.class);

        List<String> urls = createUrls(list);

        HttpConnDownloader httpConnDownloader = new HttpConnDownloader();
        Map<String, String> headers = new HashMap<>();
        String authHeader = createProxyAuthorization("ZF2018122555471fNa1","d0b3fe4012384dc1835509edad3e96df");
        headers.put(XunProxyManager.HEADER_PROXY_AUTH, authHeader);
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        headers.put("Host", "www.lagou.com");
        httpConnDownloader.setHeaders(headers);
        ProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy(XunProxyManager.IP, XunProxyManager.PORT)).longLive();
        httpConnDownloader.setProxyProvider(proxyProvider);
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String html = page.getRawText();
                        Document document = Jsoup.parse(html);
                        try {
                            String experience = Xsoup.compile("//dd[@class='job_request']/p[1]/span[3]/text()").evaluate(document).get().trim().replace("/","");
                            String edu = Xsoup.compile("//dd[@class='job_request']/p[1]/span[4]/text()").evaluate(document).get().trim().replace("/","");
                            String jobType = Xsoup.compile("//dd[@class='job_request']/p[1]/span[5]/text()").evaluate(document).get().trim().replace("/","");
                            Elements elements = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li").evaluate(document).getElements();
                            String field, devtrend, personCount, homePage, investmentAgency;
                            if (elements.size() == 4) {
                                field = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[1]/text()").evaluate(document).get().trim();
                                devtrend = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[2]/text()").evaluate(document).get().trim();
                                investmentAgency = "none";
                                personCount = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[3]/text()").evaluate(document).get().trim();
                                homePage = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[4]/a/text()").evaluate(document).get().trim();
                            } else {
                                field = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[1]/text()").evaluate(document).get().trim();
                                devtrend = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[2]/text()").evaluate(document).get().trim();
                                investmentAgency = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[3]/p/text()").evaluate(document).get().trim();
                                personCount = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[4]/text()").evaluate(document).get().trim();
                                homePage = Xsoup.compile("//div[@class='content_r']//ul[@class='c_feature']/li[5]/a/text()").evaluate(document).get().trim();
                            }

                            String jobAllure = Xsoup.compile("//div[@class='content_l fl']//dd[@class='job-advantage']/p/text()").evaluate(document).get().trim();
                            String jobDesc = Xsoup.compile("//div[@class='content_l fl']//dd[@class='job_bt']/div").evaluate(document).get().trim();
                            String jobAddr = Xsoup.compile("//div[@class='content_l fl']//dd[@class='job-address clearfix']/div[@class='work_addr']").evaluate(document).getElements().text().replace("查看地图", "").trim();

                            LagouDB lagouDB = cache.get(page.getUrl());
                            lagouDB.setExperience(experience);
                            lagouDB.setEdu(edu);
                            lagouDB.setJobType(jobType);
                            lagouDB.setField(field);
                            lagouDB.setDevtrend(devtrend);
                            lagouDB.setInvestmentAgency(investmentAgency);
                            lagouDB.setPersonCount(personCount);
                            lagouDB.setHomePage(homePage);
                            lagouDB.setJobAllure(jobAllure);
                            lagouDB.setJobDesc(jobDesc);
                            lagouDB.setJobAddr(jobAddr);
                            System.out.println(lagouDB);
                            lagouSpiderNew.insert(lagouDB);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .thread(50)
                .sleepTime(0)
                .retrySleepTime(0)
                .downloader(httpConnDownloader)
                .urls(urls)
                .run();
    }

    private static List<String> createUrls(List<LagouDB> list) {
        List<String> urls = new ArrayList<>();
        for (LagouDB lagouDB : list) {
            String url = String.format("https://www.lagou.com/jobs/%s.html", lagouDB.getPositionId());
            urls.add(url);
            cache.put(url, lagouDB);
        }
        return urls;
    }

    @Override
    public String dbname() {
        return "lagou";
    }
}
