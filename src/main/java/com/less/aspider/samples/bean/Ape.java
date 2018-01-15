package com.less.aspider.samples.bean;

/**
 * Created by deeper on 2018/1/15.
 */

public class Ape {
    private String name;
    private String album;
    private String kbps;
    private String size;
    private String url;

    public Ape(String name, String album, String kbps, String size, String url, String password) {
        this.name = name;
        this.album = album;
        this.kbps = kbps;
        this.size = size;
        this.url = url;
        this.password = password;
    }

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getKbps() {
        return kbps;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setKbps(String kbps) {
        this.kbps = kbps;
    }

    @Override
    public String toString() {
        return "Ape{" +
                "name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", kbps='" + kbps + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
