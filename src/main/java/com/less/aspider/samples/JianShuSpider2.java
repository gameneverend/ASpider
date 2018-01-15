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
import com.less.aspider.pipeline.Pipeline;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;
import com.less.aspider.samples.bean.JianShuFollower;
import com.less.aspider.samples.bean.JianShuFollowing;
import com.less.aspider.samples.bean.JianShuUser;
import com.less.aspider.samples.db.JianshuDao;
import com.less.aspider.scheduler.PriorityScheduler;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class JianShuSpider2 {
    private static JianshuDao jianshuDao = new JianshuDao();

    public static void main(String args[]) {
        // dao config
        DBHelper.setType(DBHelper.TYPE_MYSQL);
        DBHelper.setDBName("jianshu");
        jianshuDao.createTable();

         // Proxy 代理设置
        String path = System.getProperty("user.dir") + File.separator + "ASpidder" + File.separator + "src/main/java/proxy.txt";
        ProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy("113.209.100.170", 23128,"sy0bwptrqo","kb46o4cp0i"));
        Downloader downloader = new HttpConnDownloader();
        // headers 设置(具有时效性)
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "s0.jianshuapi.com");
        headers.put("X-App-Name", "haruki");
        headers.put("X-App-Version", "3.2.0");
        headers.put("X-Device-Guid", "127051030369235");
        headers.put("X-Timestamp", "1516000407");
        headers.put("X-Auth-1", "beb8952985c1a75e7c7d6b341073c246");
        downloader.setHeaders(headers);
        downloader.setProxyProvider(proxyProvider);

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
                                String slug = user.getSlug();
                                String homePage = "https://s0.jianshuapi.com/v2/users/" + userId;
                                String blog = "https://www.jianshu.com/u/" + slug;
                                // 优先抓取个人主页
                                page.addTargetRequests(new Request(homePage,null,1));
                            }
                        } else if (url.contains("followers?app[name]=haruki")) {
                            // 粉丝列表
                            Type type = new TypeToken<ArrayList<JianShuFollower>>() {}.getType();
                            List<JianShuFollower> results = gson.fromJson(page.getRawText(), type);
                            for (JianShuFollower follower : results) {
                                int userId = follower.getId();
                                String slug = follower.getSlug();
                                String homePage = "https://s0.jianshuapi.com/v2/users/" + userId;
                                String blog = "https://www.jianshu.com/u/" + slug;
                                // 优先抓取个人主页
                                page.addTargetRequests(new Request(homePage,null,1));
                            }
                        } else {
                            // user homePage
                            JianShuUser jianShuUser = gson.fromJson(page.getRawText(), JianShuUser.class);
                            jianShuUser.setJsonText(page.getRawText());
                            page.putField("user", jianShuUser);
                            int followers_count = jianShuUser.getFollowers_count();
                            if (followers_count > 0) {
                                int userId = jianShuUser.getId();

                                String followerUrl = "https://s0.jianshuapi.com/v1/users/" + userId + "/followers?app[name]=haruki&app[version]=3.2.0&device[guid]=127051030369235&count=1000&page=1";
                                page.addTargetRequests(new Request(followerUrl,null,-1));

                                String followUrl = "https://s0.jianshuapi.com/v1/users/" + userId + "/following?app[name]=haruki&app[version]=3.2.0&device[guid]=127051030369235&count=1000&page=1";
                                page.addTargetRequests(new Request(followUrl,null,-1));
                            }
                        }
                    }
                })
                .thread(30)
                .downloader(downloader)
                .scheduler(new PriorityScheduler())
                .sleepTime(0)
                .retrySleepTime(0)
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(Map<String, Object> fields) {
                        for (Map.Entry<String, Object> entry : fields.entrySet()) {
                            JianShuUser jianShuUser = (JianShuUser) entry.getValue();
                            jianshuDao.save(jianShuUser);
                        }
                    }
                })
                .urls(null,"https://s0.jianshuapi.com/v2/users/5c62892e3d47")
                .run();
    }
}
