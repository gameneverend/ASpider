package com.less.aspider.samples;

import com.google.gson.Gson;
import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.bean.Request;
import com.less.aspider.db.DBHelper;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.pipeline.Pipeline;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.samples.bean.JianSpecial;
import com.less.aspider.samples.db.JianshuDao;
import com.less.aspider.scheduler.PriorityScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class JianShuSpider3 {
    private static JianshuDao jianshuDao = new JianshuDao();

    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C");
        list.add("C++");
        list.add("爬虫");
        list.add("JavaScript");
        list.add("Go");
        list.add("编程");

        // dao config
        DBHelper.setType(DBHelper.TYPE_MYSQL);
        DBHelper.setDBName("jianshu_zhuanti");
        jianshuDao.createTable();

        Downloader downloader = new HttpConnDownloader();
        // headers 设置(具有时效性)
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "s0.jianshuapi.com");
        headers.put("X-App-Name", "haruki");
        headers.put("X-App-Version", "3.2.0");
        headers.put("X-Device-Guid", "127051030369235");
        headers.put("X-Timestamp", "1516279463");
        headers.put("X-Auth-1", "a84c9d558e55db9ba7d55a2931ef9dba");
        downloader.setHeaders(headers);

        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        Gson gson = new Gson();
                        JianSpecial jianSpecial = gson.fromJson(page.getRawText(), JianSpecial.class);

                        if (url.startsWith("https://api.jianshu.io/v2/collections/")) {
                            System.out.println("==========> " + page.getRawText());
                            // 专题
                            Request lastRequest = page.getOriginRequest();
                            Integer lastPage = (Integer) lastRequest.getExtra("currentPage");
                            String lastBaseUrl = (String) lastRequest.getExtra("baseUrl");

                            if (lastPage == null) {
                                lastPage = 1;
                            }
                            if (lastBaseUrl == null) {
                                lastBaseUrl = "https://api.jianshu.io/v2/collections/";
                            }

                            int nextPage = lastPage + 1;
                            // 40000
                            if (nextPage > 2) {
                                return;
                            }
                            Request request = new Request();
                            String nextUrl = lastBaseUrl + nextPage;
                            request.setUrl(nextUrl);
                            request.putExtra("currentPage", lastPage + 1);
                            request.putExtra("baseUrl", lastBaseUrl);
                            page.addTargetRequest(request);

                        } else {
                            // 专题下的用户
                            // https://api.jianshu.io/v2/collections/517169/subscribers?page=1&count=15
                        }
                    }
                })
                .thread(2)
                .downloader(downloader)
                .scheduler(new PriorityScheduler())
                .sleepTime(3000)
                // 只有设置此项true: 错误的Page才会返回,否则默认重试-> 指定次数
                .errorReturn(true)
                .retrySleepTime(1000)
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(Map<String, Object> fields) {
                        for (Map.Entry<String, Object> entry : fields.entrySet()) {

                        }
                    }
                })
                //
                .urls("https://api.jianshu.io/v2/collections/140000")
                .run();
    }
}
