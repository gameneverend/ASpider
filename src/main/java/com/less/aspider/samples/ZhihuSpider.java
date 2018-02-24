package com.less.aspider.samples;

import com.google.gson.Gson;
import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.bean.Request;
import com.less.aspider.dao.DatabaseHelper;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.samples.bean.ZhihuFollowees;
import com.less.aspider.samples.bean.ZhihuPersonDB;
import com.less.aspider.scheduler.BDBScheduler;
import com.less.aspider.scheduler.PriorityScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deeper on 2018/2/14.
 */

public class ZhihuSpider extends DatabaseHelper {

    public static void main(String[] args) {
        String startUrl = "https://api.zhihu.com/people/3d666e0305654c17fa85494c8e633296/followees?offset=15";
        final String followeesUrl = "https://api.zhihu.com/people/%s/followees?offset=0";

        final ZhihuSpider zhihuSpider = new ZhihuSpider();
        zhihuSpider.createTable(ZhihuPersonDB.class);

        HttpConnDownloader httpConnDownloader = new HttpConnDownloader();
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "api.zhihu.com");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        headers.put("Cookie", "_zap=72644d0d-dded-4703-aaf7-6901612845c7; q_c1=391baee77b4549fe89bab583f032c2aa|1516764692000|1516764692000; d_c0=\"AGCs654qHA2PTovW3WVBJMpmwDS8YA2TVbQ=|1518008520\"; __utma=51854390.1673119202.1518936656.1518936656.1518936656.1; __utmc=51854390; __utmz=51854390.1518936656.1.1.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/people/yu-shi-jian-sai-pao-63/activities; __utmv=51854390.100--|2=registration_date=20180218=1^3=entry_date=20170619=1; l_cap_id=\"YzUyMjI0NTBhOTFlNGY0NTg4YTIxZTY5ZGM2OWQ4YzA=|1518944326|a64633ac97006972f8b350c2e8c08dba6e033a1f\"; r_cap_id=\"MmEwY2E5MjRlNzdjNDkwZjllYjZjNGRlZWM1ODhmOTA=|1518944326|565317ddcf0636fea39a3abaf8da49da98fd6a7c\"; cap_id=\"YmJhYTQ3OTdiMGM0NDZlZGFlODhiZWYxYzc4OTNlMDY=|1518944326|e2bec0400d94a336229fff219b26895d692a7857\"; aliyungf_tc=AQAAAHF8vj2BzwQAtXrqKn7d59J4LqE8; capsion_ticket=\"2|1:0|10:1518944428|14:capsion_ticket|44:MzMzZjI3NGRkOTQ4NDM1MzhlZWY3YWEyOWE1OWMxN2U=|397d596c34d37c3e9b8f27ab6f8980ecc810355be7b024e5bcb5c3c18d8a0dc7\"; z_c0=Mi4xWjN2Y0J3QUFBQUFBWUt6cm5pb2NEUmNBQUFCaEFsVk50WTUyV3dDS2xyN0U5TU9ZY2U5Y3RBREJhamJOYklhN0t3|1518944437|0f8f1f4fc50c641170ec1184862ff24d2164cfd3; _xsrf=c2aed93867a54b1971e9a891183393ef; unlock_ticket=\"ADDs00D_KQ0XAAAAYQJVTcNHiVqe1ayUKWIm0Jy-q7IetIWxLZ31tw==\"");
        headers.put("If-None-Match", "W/\"bd21030fe1e6f934a39829b8be9946960c6f37fd\"");
        httpConnDownloader.setHeaders(headers);

        ASpider.create()
                .scheduler(new PriorityScheduler())
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String html = page.getRawText();
                        System.out.println("html: " + html);

                        Gson gson = new Gson();
                        if (page.getUrl().contains("followees")) {
                            ZhihuFollowees zhihuFollowees= gson.fromJson(html, ZhihuFollowees.class);
                            if (!zhihuFollowees.getPaging().isIs_end()) {
                                String nextFolloweesUrl = zhihuFollowees.getPaging().getNext();
                                page.addTargetRequest(new Request(nextFolloweesUrl, 0));

                                List<ZhihuFollowees.DataBean> list = zhihuFollowees.getData();
                                for (ZhihuFollowees.DataBean dataBean : list) {
                                    String personUrl = dataBean.getUrl();
                                    page.addTargetRequest(new Request(personUrl, 1));
                                }
                            }
                        } else {
                            ZhihuPersonDB zhihuPersonDB = gson.fromJson(html, ZhihuPersonDB.class);
                            zhihuSpider.insert(zhihuPersonDB);
                            String userId = zhihuPersonDB.getId();
                            page.addTargetRequest(new Request(String.format(followeesUrl, userId), -1));
                        }
                    }
                })
                .downloader(httpConnDownloader)
                .scheduler(new BDBScheduler())
                .sleepTime(0)
                .retrySleepTime(0)
                .thread(50)
                .urls(startUrl)
                .run();
    }

    @Override
    public String dbname() {
        return "zhihuspider";
    }
}
