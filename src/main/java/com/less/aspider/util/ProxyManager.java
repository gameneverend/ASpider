package com.less.aspider.util;

import com.less.aspider.bean.Proxy;
import com.less.aspider.bean.Request;
import com.less.aspider.http.HttpConnUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProxyManager {

	private String url;

	private CountableThreadPool threadPool = new CountableThreadPool(100);

	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	private Pattern pattern;

	private List<Integer> ports = new ArrayList<>();

	public ProxyManager() {
		pattern = Pattern.compile(RegexConstants.REGEX_IP);
		ports.add(80);
		ports.add(8080);
		ports.add(8118);
		ports.add(3128);
		ports.add(8888);
		ports.add(9999);
		ports.add(8118);
		ports.add(53281);
		ports.add(9000);
		ports.add(6666);
		ports.add(54318);
		ports.add(9797);
		ports.add(808);
		ports.add(6666);
		ports.add(808);
		ports.add(8010);
	}

	public static void main(String args[]) throws Exception {
		// http://ttvp.daxiangip.com/ip/?tid=558659427668804&num=100&operator=2&protocol=http
		// http://ttvp.daxiangip.com/ip/?tid=558659427668804&num=100&operator=2&protocol=http&foreign=only
		String url = "http://www.xicidaili.com/";
		ProxyManager manager = new ProxyManager();
		manager.start(url);
	}

	public void start(String url) throws Exception {
		this.url = url;
		String html = downloadPage();
		parsePage(html);
		checkAll();
	}

	private void checkAll() {
		String url = null;
		while((url = queue.poll()) != null){
			check(url);
		}
	}

	private void parsePage(String html) {
		Matcher matcher = pattern.matcher(html);
		while(matcher.find()){
			String ip = matcher.group();
			queue.add(ip);
		}
	}

	private String downloadPage() throws Exception {
		byte[] datas = HttpConnUtils.getDefault().sendRequest(new Request(url));
		String html = new String(datas);
		return html;
	}

	public void check(final String ip){
		threadPool.execute(new Runnable() {

			@Override
			public void run() {
				for(int port : ports){
					try {
						byte[] datas = HttpConnUtils.getDefault().sendRequestByProxy(new Request("http://www.qq.com/robots.txt"), new Proxy(ip, port));
						String html = new String(datas);
						if (html.contains("http://www.qq.com/sitemap_index.xml")) {
							System.err.println(ip + ":" + port);
							break;
						}
					} catch (Exception ignore) {

					}
				}
			}
		});
	}
}