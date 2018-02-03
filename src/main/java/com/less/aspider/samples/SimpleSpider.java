package com.less.aspider.samples;

import com.less.aspider.ASpider;
import com.less.aspider.bean.Page;
import com.less.aspider.processor.PageProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleSpider {
    public static String TAG = SimpleSpider.class.getSimpleName();
    public static final String REGEX_URL = "(http|https)+://[^\\s|\\?|&|'|\"]+(com|cn|org|net)+?";
    public static final Pattern pattern = Pattern.compile(REGEX_URL);

    public static void start(String startUrl) {
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
                        String url = page.getUrl();
                        page.putField("page",page.getRawText());
                        Matcher matcher = pattern.matcher(page.getRawText());
                        while(matcher.find()){
                            String result = matcher.group();
                            page.addTargetRequestsNoReferer(result);
                        }
                    }
                })
                .thread(2)
                .sleepTime(0)
                .retrySleepTime(0)
                .urls(startUrl)
                .runAsync();
    }

    public static void main(String[] args) {
        start("http://www.java1234.com");
    }
}
