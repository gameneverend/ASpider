package com.less.aspider.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by deeper on 2017/12/17.
 */

public class Request implements Serializable {

    private static final long serialVersionUID = 2062192774891352043L;

    public static final String CYCLE_TRIED_TIMES = "_cycle_tried_times";
    private Map<String, Object> extras;

    private String url;

    private String method;

    private String charset;

    private long priority;

    private Map<String, String> cookies = new HashMap<String, String>();

    private Map<String, String> headers = new HashMap<String, String>();

    public Request(String url) {
        this.url = url;
    }

    public Object getExtra(String key) {
        if (extras == null) {
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

    public Request setPriority(long priority) {
        this.priority = priority;
        return this;
    }
}
