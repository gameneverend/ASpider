package com.less.aspider.samples;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.downloader.Downloader;
import com.less.aspider.downloader.HttpConnDownloader;
import com.less.aspider.processor.PageProcessor;
import com.less.aspider.proxy.ProxyProvider;
import com.less.aspider.proxy.SimpleProxyProvider;
import com.less.aspider.samples.bean.JianShuFollowing;
import com.less.aspider.samples.bean.JianShuUser;
import com.less.aspider.util.L;

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

    public static void main(String args[]) {
         // Proxy 代理设置
        String path = System.getProperty("user.dir") + File.separator + "ASpidder" + File.separator + "src/main/java/proxy.txt";
        ProxyProvider proxyProvider = SimpleProxyProvider.from(path);
        Downloader downloader = new HttpConnDownloader();
        // headers 设置(具有时效性)
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", "s0.jianshuapi.com");
        headers.put("X-App-Name", "haruki");
        headers.put("X-App-Version", "3.2.0");
        headers.put("X-Device-Guid", "127051030369235");
        headers.put("X-Timestamp", "1515164819");
        headers.put("X-Auth-1", "0c4b8bcc5cfe8ce81391b0c8b38cd535");
        downloader.setHeaders(headers);
        // downloader.setProxyProvider(proxyProvider);

        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        Gson gson = new Gson();
                        if (url.contains("following?app[name]=haruki")) {
                            page.putField("following",page.getRawText());

                            // following
                            Type type = new TypeToken<ArrayList<JianShuFollowing>>() {}.getType();
                            List<JianShuFollowing> results = gson.fromJson(page.getRawText(), type);

                            List<String> urls = new ArrayList<String>();
                            for (JianShuFollowing user : results) {
                                // userId 非博客地址
                                int userId = user.getId();
                                String slug = user.getSlug();
                                String homePage = "https://s0.jianshuapi.com/v2/users/" + userId;
                                urls.add(homePage);
                                String blog = "https://www.jianshu.com/u/" + slug;
                            }
                            page.addTargetRequests(null, urls);
                        } else {
                            page.putField("user",page.getRawText());

                            // user homePage
                            L.d("HomePage ======> " + page.getUrl());
                            JianShuUser jianShuUser = gson.fromJson(page.getRawText(), JianShuUser.class);
                            int followers_count = jianShuUser.getFollowers_count();
                            if (followers_count > 50) {
                                int userId = jianShuUser.getId();
                                String followUrl = "https://s0.jianshuapi.com/v1/users/" + userId + "/following?app[name]=haruki&app[version]=3.2.0&device[guid]=833051020369123&count=15";
                                page.addTargetRequests(null,followUrl);
                            }
                        }
                    }
                })
                .thread(3)
                .downloader(downloader)
                .sleepTime(3000)
                .retrySleepTime(1000)
                .urls(null,"https://s0.jianshuapi.com/v2/users/abc8086489c7")
                .run();
    }
}
