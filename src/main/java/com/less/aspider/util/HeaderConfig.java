package com.less.aspider.util;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfig {

	private Map<String,String> headers = new HashMap<>();

	public HeaderConfig() {
		init();
	}

	private void init() {

		// headers.put("Cookie", "");
		// headers.put("Referer", "");
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate, sdch");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
	}

	public Map<String,String> defaultHeaders(){
		return headers;
	}
}
