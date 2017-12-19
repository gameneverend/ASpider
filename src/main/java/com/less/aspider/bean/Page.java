package com.less.aspider.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deeper on 2017/12/19.
 */

public class Page {

    private boolean skip;

    private Map<String, Object> fields = new LinkedHashMap<String, Object>();

    private boolean downloadSuccess;

    private List<Request> targetRequests = new ArrayList<Request>();

    private String rawText;

    public void setDownloadSuccess(boolean downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }

    public boolean isDownloadSuccess() {
        return downloadSuccess;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public boolean isSkip() {
        return skip;
    }

    public List<Request> getTargetRequests() {
        return targetRequests;
    }

    public void addTargetRequests(List<String> urls) {
        for (String url : urls) {
            targetRequests.add(new Request(url));
        }
    }

    public <T> T getField(String key) {
        Object o = fields.get(key);
        if (o == null) {
            return null;
        }
        return (T) fields.get(key);
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public <T> void putField(String key, T value) {
        fields.put(key, value);
    }

    public void setRawText(String text) {
        this.rawText = text;
    }

    public String getRawText() {
        return rawText;
    }
}
