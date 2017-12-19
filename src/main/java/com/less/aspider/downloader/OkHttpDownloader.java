package com.less.aspider.downloader;

import com.less.aspider.bean.Page;
import com.less.aspider.bean.Request;
import com.less.aspider.util.Singleton;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Response;


/**
 *
 * @author deeper
 * @date 2017/12/17
 */

public class OkHttpDownloader implements Downloader {

    static public OkHttpClient getDefault() {
        return gDefault.get();
    }

    private static final Singleton<OkHttpClient> gDefault = new Singleton<OkHttpClient>() {

        @Override
        protected OkHttpClient create() {
            OkHttpClient okHttpClient = new OkHttpClient();
            return okHttpClient;
        }
    };

    @Override
    public Page download(Request request) {
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url(request.getUrl())
                .get();
        Page page = new Page();
        try {
            Response response = getDefault().newCall(builder.build())
                    .execute();
            page.setRawText(response.body().string());
            page.setDownloadSuccess(true);
            return page;
        } catch (IOException e) {
            page.setDownloadSuccess(false);
            e.printStackTrace();
        }
        return page;
    }
}
