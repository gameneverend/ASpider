package com.less.aspider.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by deeper on 2017/12/17.
 */

public class Request implements Serializable {

    private static final long serialVersionUID = 2062192774891352043L;

    public static final int POST_TYPE_JSON = 0x000001;
    public static final int POST_TYPE_FORM = 0x000002;

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    public static final String CYCLE_TRIED_TIMES = "_cycle_tried_times";

    private Map<String, Object> extras;

    private String url;

    private String refererUrl;

    private String method = METHOD_GET;

    private int postType = POST_TYPE_FORM;

    private String charset;

    private long priority;

    private Map<String, String> cookies;

    private Map<String, String> headers;

    private Map<String, Object> parameters;

    public Request() {

    }

    public Request(String url) {
        this.url = url;
    }

    public Request(String url, String refererUrl) {
        this.url = url;
        this.refererUrl = refererUrl;
    }

    public Request(String url, long priority) {
        this.url = url;
        this.priority = priority;
    }

    public Request(String url, String refererUrl, long priority) {
        this.url = url;
        this.refererUrl = refererUrl;
        this.priority = priority;
    }

    public Object getExtra(String key) {
        if (null == extras) {
            return null;
        }
        return extras.get(key);
    }

    public Request putExtra(String key, Object value) {
        if (extras == null) {
            extras = new HashMap<String, Object>();
        }
        extras.put(key, value);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public Request setPriority(long priority) {
        this.priority = priority;
        return this;
    }

    public long getPriority() {
        return priority;
    }

    public void setRefererUrl(String refererUrl){
        this.refererUrl = refererUrl;
    }

    public String getRefererUrl() {
        return refererUrl;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> addParameter(String key, Object value) {
        if (parameters == null) {
            parameters = new HashMap<String, Object>();
        }
        parameters.put(key, value);
        return parameters;
    }
}
