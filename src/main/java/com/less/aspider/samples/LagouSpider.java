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

    public static void main(String args[]) {
        final LagouDao lagouDao = new LagouDao();
        lagouDao.createTable();

        String baseUrl = "xx";
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++) {
            String url = String.format(baseUrl, "全国", "C++", i);
            urls.add(url);
        }
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

}
