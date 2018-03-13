package com.less.aspider.samples.bean;

import com.less.aspider.dao.Column;
import com.less.aspider.dao.Table;

/**
 * @author deeper
 * @date 2018/3/13
 */

@Table
public class JavaPdf {

    @Column(value = "id", columnDefinition = "int primary key auto_increment")
    private int id;

    @Column
    private String url;

    @Column
    private String title;

    @Column(value = "panUrl", columnDefinition = "varchar(255) not null unique")
    private String panUrl;

    @Column(value = "passwd")
    private String passwd;

    public JavaPdf(String title, String panUrl, String passwd, String url) {
        this.title = title;
        this.panUrl = panUrl;
        this.passwd = passwd;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPanUrl() {
        return panUrl;
    }

    public void setPanUrl(String panUrl) {
        this.panUrl = panUrl;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
