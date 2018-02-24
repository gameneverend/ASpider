package com.less.aspider.samples;

import com.google.gson.Gson;
import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.samples.bean.Lagou;
import com.less.aspider.samples.db.LagouDao;
import com.less.aspider.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deeper on 2018/2/6.
 */

public class LagouSpider {

    private static int pageCount = 350;
    private static String baseUrl = "https://m.lagou.com/search.json?city=%s&positionName=%s&pageNo=%d&pageSize=15";

    public static void main(String args[]) {
        final LagouDao lagouDao = new LagouDao();
        lagouDao.createTable();

        List<String> urls = createUrls("Java", "Android", "C", "Python", "爬虫", "ios", "前端", "php", "数据挖掘", "node.js", "C#", "go", "全栈", "移动");

        System.out.println(urls);
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        try {
                            L.d(page.getRawText());

                            Gson gson = new Gson();
                            Lagou lagou = gson.fromJson(page.getRawText(), Lagou.class);
                            int totalCount = Integer.parseInt(lagou.getContent().getData().getPage().getTotalCount());
                            int pageSize = lagou.getContent().getData().getPage().getPageSize();
                            int pageCount = totalCount / pageSize + 1;
                            System.out.println("pageCount : " + pageCount);
                            lagouDao.save(lagou);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .thread(1)
                .sleepTime(1500)
                .retrySleepTime(500)
                .urls(urls)
                .run();
    }

    private static List<String> createUrls(String... names) {
        List<String> urls = new ArrayList<>();
        for (String name : names) {
            for (int i = 1; i <= pageCount; i++) {
                String url = String.format(baseUrl, "全国", name, i);
                urls.add(url);
            }
        }
        return urls;
    }
}
