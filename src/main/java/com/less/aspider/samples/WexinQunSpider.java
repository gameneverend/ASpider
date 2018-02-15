package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.bean.Request;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.samples.bean.Wxqun;
import com.less.aspider.samples.db.WxqunDao;
import com.less.aspider.scheduler.PriorityScheduler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.xsoup.Xsoup;

public class WexinQunSpider {

    /**
     * 测试链接
      */
    public static final String testUrl = "http://www.souweixin.com/";

    /**
     * 首页
     */
    public static final String startUrl = "http://www.souweixin.com";

    /**
     * 微信公众号
     */
    public static final String wx_gzh_url = "http://www\\.souweixin\\.com/openid\\?id=\\d+";

    /**
     * 个人微信
     */
    public static final String wx_person_url = "http://www\\.souweixin\\.com/personal\\?id=\\d+";

    /**
     * 微信群
     */
    public static final String wx_group_url = "http://www\\.souweixin\\.com/group\\?id=\\d+";

    public static void main(String[] args) {
        final WxqunDao wxqunDao = new WxqunDao();
        wxqunDao.createTable();
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        Document document = Jsoup.parse(page.getRawText());
                        String url = page.getUrl();
                        if (url.matches(wx_gzh_url) || url.matches(wx_person_url) || url.matches(wx_group_url)) {
                            String name = Xsoup.compile("//div[@class=\"code_info\"]/h1").evaluate(document).getElements().get(0).text().trim();
                            String desc = Xsoup.compile("//p[@class=\"f18\"]").evaluate(document).getElements().get(0).text().trim();
                            String info_wx = Xsoup.compile("//div[@class=\"green_bg\"]/p[1]").evaluate(document).getElements().get(0).text().trim();
                            String info_qq = Xsoup.compile("//div[@class=\"green_bg\"]/p[2]").evaluate(document).getElements().get(0).text().trim();
                            String hotDegress = Xsoup.compile("//div[@class=\"hot_views\"]/p[1]").evaluate(document).getElements().get(0).text().trim();
                            String type = Xsoup.compile("//div[@class=\"code_info\"]/div/ul/li[1]/a").evaluate(document).getElements().get(0).text().trim();
                            String area = Xsoup.compile("//div[@class=\"code_info\"]/div/ul/li[2]/a").evaluate(document).getElements().get(0).text().trim();
                            String time = Xsoup.compile("//div[@class=\"code_info\"]/div/ul/li[3]").evaluate(document).getElements().get(0).text().trim();
                            String tags = Xsoup.compile("//div[@class=\"code_info\"]/div/ul/li[4]").evaluate(document).getElements().get(0).text().trim();
                            String img_head = Xsoup.compile("//div[@class=\"page_con\"]//div[@class=\"code_avater\"]//img").evaluate(document).getElements().get(0).attr("src").trim();
                            String qrcode = Xsoup.compile("//div[@class=\"page_con\"]//div[@class=\"code_pic\"]//img[@src]").evaluate(document).getElements().get(0).attr("src").trim();
                            wxqunDao.save(new Wxqun(name, desc, info_wx, info_qq, hotDegress, type, area, time, tags, img_head, qrcode));
                        }
                        List<String> urls = extractUrls(document);
                        for (String u : urls) {
                            if (u.contains("?id=")) {
                                page.addTargetRequest(new Request(u, 0));
                            } else {
                                page.addTargetRequest(new Request(u, -1));
                            }
                        }
                    }
                })
                .thread(100)
                .sleepTime(0)
                .retrySleepTime(0)
                .scheduler(new PriorityScheduler())
                .urls(testUrl)
                .run();
    }

    private static List<String> extractUrls(Document document) {
        List<String> urls = new ArrayList<>();
        Elements elements = document.select("a");
        for (Element e : elements) {
            String href = e.attr("href");
            if (!href.startsWith("http://") && !href.contains("javascript") && !href.contains("search")) {
                if (href.contains("personal") || href.contains("openid") || href.contains("group")) {
                    href = startUrl + href;
                    urls.add(href);
                }
            }
        }
        return urls;
    }
}
