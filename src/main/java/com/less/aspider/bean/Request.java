package com.less.aspider.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by deeper on 2017/12/17.
 */

public class Request implements Serializable {

    private static final long serialVersionUID = 2062192774891352043L;

    private String url;

    private String method;

    private String charset;

    private long priority;

    private Map<String, String> cookies = new HashMap<String, String>();

    private Map<String, String> headers = new HashMap<String, String>();

    public Request(String url) {
        this.url = url;
    }
}
