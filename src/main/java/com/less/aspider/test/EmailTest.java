package com.less.aspider.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * Created by deeper on 2018/2/14.
 */

public class EmailTest {

    public static void main(String[] args) throws Exception {
        List<String> list = FileUtils.readLines(new File("F:/email_jian.txt"), "utf-8");
        String[] emails = new String[list.size()];
        list.toArray(emails);

        String html = FileUtils.readFileToString(new File("F:/mail.html"), "utf-8");
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setCharset("UTF-8");
        URL url = new URL("https://www.jianshu.com");
        email.setDataSourceResolver(new DataSourceUrlResolver(url));

        // smtp.163.com, smtp.qq.com, smtp.googlemail.com
        email.setHostName("smtp.163.com");
        email.setSmtpPort(25);
        // 用户名和密码为邮箱的账号和密码（不需要进行base64编码）
        email.setAuthenticator(new DefaultAuthenticator("xx", "xx"));
        email.setSSLOnConnect(true);
        email.setFrom("xx@163.com","xxx");
        email.setSubject("有人@您一下！");
        email.setTextMsg("Your email client does not support HTML messages");
        email.setHtmlMsg(html);
        email.addTo(emails);
        String result = email.send();
        System.out.println("result: " + result);
    }
}
