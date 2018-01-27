package com.less.aspider.samples;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.bean.Proxy;
import com.less.aspider.bean.Request;
import com.less.aspider.db.DBHelper;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.http.HttpConnUtils;
import com.less.aspider.pipeline.Pipeline;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;
import com.less.aspider.samples.bean.JianShuFollower;
import com.less.aspider.samples.bean.JianShuFollowing;
import com.less.aspider.samples.bean.JianShuUser;
import com.less.aspider.samples.db.JianshuDao;
import com.less.aspider.scheduler.BDBScheduler;
import com.less.aspider.util.XunProxyManager;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.less.aspider.util.XunProxyManager.createProxyAuthorization;

/**
 * @author Administrator
 */
public class JianShuSpider2 {

    private static final int QUERY_COUNT = 50;

    private static JianshuDao jianshuDao = new JianshuDao();

    public static void main(String args[]) {
        configDB();

        String authHeader = createProxyAuthorization("xx","xx");
        Downloader downloader = new HttpConnDownloader();

        Map<String, String> headers = new HashMap<>();
        headers.put(XunProxyManager.HEADER_PROXY_AUTH, authHeader);
        downloader.setHeaders(headers);

        ProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy(XunProxyManager.IP, XunProxyManager.PORT));
        proxyProvider.longLive();
        downloader.setProxyProvider(proxyProvider);

        timerWork("F:\\jconfig.json", 5, true);

        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        Gson gson = new Gson();
                        if (url.contains("following?app[name]=haruki")) {
                            // 关注列表
                            Type type = new TypeToken<ArrayList<JianShuFollowing>>() {}.getType();
                            List<JianShuFollowing> results = gson.fromJson(page.getRawText(), type);

                            for (JianShuFollowing user : results) {
                                // userId 非博客地址
                                int userId = user.getId();
                                String homePage = "https://s0.jianshuapi.com/v2/users/" + userId;
                                // 优先抓取个人主页
                                page.addTargetRequest(new Request(homePage,2));
                            }
                        } else if (url.contains("followers?app[name]=haruki")) {
                            // 粉丝列表
                            Type type = new TypeToken<ArrayList<JianShuFollower>>() {}.getType();
                            List<JianShuFollower> results = gson.fromJson(page.getRawText(), type);
                            for (JianShuFollower follower : results) {
                                int userId = follower.getId();
                                String homePage = "https://s0.jianshuapi.com/v2/users/" + userId;
                                // 优先抓取个人主页
                                page.addTargetRequest(new Request(homePage,1));
                            }
                        } else {
                            // user homePage
                            JianShuUser jianShuUser = gson.fromJson(page.getRawText(), JianShuUser.class);
                            page.putField("user", jianShuUser);
                            int followers_count = jianShuUser.getFollowers_count();
                            if (followers_count > -5) {
                                int userId = jianShuUser.getId();

                                String followUrl = "https://s0.jianshuapi.com/v1/users/" + userId + "/following?app[name]=haruki&app[version]=3.2.0&device[guid]=127051030369235&count=" +QUERY_COUNT+ "&page=1";
                                page.addTargetRequest(new Request(followUrl,-1));

                                String followerUrl = "https://s0.jianshuapi.com/v1/users/" + userId + "/followers?app[name]=haruki&app[version]=3.2.0&device[guid]=127051030369235&count=" + QUERY_COUNT+ "&page=1";
                                page.addTargetRequest(new Request(followerUrl,-2));
                            }
                        }
                    }
                })
                .thread(30)
                .downloader(downloader)
                .scheduler(new BDBScheduler())
                .sleepTime(0)
                .retrySleepTime(0)
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(Map<String, Object> fields) {
                        for (Map.Entry<String, Object> entry : fields.entrySet()) {
                            JianShuUser jianShuUser = (JianShuUser) entry.getValue();
                            jianshuDao.save(jianShuUser);

                            System.out.println(jianShuUser.toString());
                        }
                    }
                })
                .urls("https://s0.jianshuapi.com/v2/users/1110734")
                .run();
    }

    private static void configDB() {
        DBHelper.setType(DBHelper.TYPE_MYSQL);
        DBHelper.setDBName("jianshu_new");
        jianshuDao.createTable();
    }

    public static class JHeader {
        String timestamp;
        String auth;
    }

    public static void timerWork(final String path, int period, boolean daemon) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(daemon).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                File file = new File(path);
                if (file.exists()) {
                    try {
                        Gson gson = new Gson();
                        JHeader jHeader = gson.fromJson(new FileReader(file), JHeader.class);
                        HttpConnUtils.getDefault().addHeader("X-Auth-1", jHeader.auth);
                        HttpConnUtils.getDefault().addHeader("X-Timestamp", jHeader.timestamp);

                        System.out.println(HttpConnUtils.getDefault().getHeaders());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("temp proxy file not exsist!");
                }
            }
        }, 0, period, TimeUnit.MINUTES);
    }
}
