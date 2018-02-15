package com.less.aspider.samples.bean;

/**
 * Created by deeper on 2018/2/9.
 */

public class Wxqun {

    String name;
    String descption;
    String info_wx;
    String info_qq;
    String hotDegress;
    String type;
    String area;
    String time;
    String tags;
    String img_head;
    String qrcode;

    public Wxqun(String name, String desc, String info_wx, String info_qq, String hotDegress, String type, String area, String time, String tags, String img_head, String qrcode) {
        this.name = name;
        this.descption = desc;
        this.info_wx = info_wx;
        this.info_qq = info_qq;
        this.hotDegress = hotDegress;
        this.type = type;
        this.area = area;
        this.time = time;
        this.tags = tags;
        this.img_head = img_head;
        this.qrcode = qrcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo_wx() {
        return info_wx;
    }

    public void setInfo_wx(String info_wx) {
        this.info_wx = info_wx;
    }

    public String getInfo_qq() {
        return info_qq;
    }

    public void setInfo_qq(String info_qq) {
        this.info_qq = info_qq;
    }

    public String getHotDegress() {
        return hotDegress;
    }

    public String getDescption() {
        return descption;
    }

    public void setDescption(String descption) {
        this.descption = descption;
    }

    public void setHotDegress(String hotDegress) {
        this.hotDegress = hotDegress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImg_head() {
        return img_head;
    }

    public void setImg_head(String img_head) {
        this.img_head = img_head;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @Override
    public String toString() {
        return "Wxqun{" +
                "name='" + name + '\'' +
                ", desc='" + descption + '\'' +
                ", info_wx='" + info_wx + '\'' +
                ", info_qq='" + info_qq + '\'' +
                ", hotDegress='" + hotDegress + '\'' +
                ", type='" + type + '\'' +
                ", area='" + area + '\'' +
                ", time='" + time + '\'' +
                ", tags='" + tags + '\'' +
                ", img_head='" + img_head + '\'' +
                ", qrcode='" + qrcode + '\'' +
                '}';
    }
}