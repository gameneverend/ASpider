package com.less.aspider.samples.bean;

/**
 * Created by deeper on 2018/1/5.
 */

public class JianShuFollowing {

    /**
     * id : 3678149
     * email : lingyuze@jianshu.com
     * nickname : 简宝玉
     * gender : 0
     * slug : b52ff888fd17
     * avatar : http://upload.jianshu.io/users/upload_avatars/3678149/b8a58e70-1126-48c9-97e2-8f21a31dfa94.png
     * intro_compiled : 我是简书家的宝玉~<br><br>在微信上搜索公众号“简宝玉”（jianshubaoyu），即可了解关于简书的一切事情。有问题，找宝玉！简宝玉同志会专程为您解疑答惑。<br><br>发送 拒稿/专题投稿，了解简书专题收稿相关规则~
     * is_following_user : false
     * is_followed_by_user : false
     * total_likes_received : 10277
     * total_wordage : 66889
     */

    private int id;
    private String email;
    private String nickname;
    private int gender;
    private String slug;
    private String avatar;
    private String intro_compiled;
    private boolean is_following_user;
    private boolean is_followed_by_user;
    private int total_likes_received;
    private int total_wordage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntro_compiled() {
        return intro_compiled;
    }

    public void setIntro_compiled(String intro_compiled) {
        this.intro_compiled = intro_compiled;
    }

    public boolean isIs_following_user() {
        return is_following_user;
    }

    public void setIs_following_user(boolean is_following_user) {
        this.is_following_user = is_following_user;
    }

    public boolean isIs_followed_by_user() {
        return is_followed_by_user;
    }

    public void setIs_followed_by_user(boolean is_followed_by_user) {
        this.is_followed_by_user = is_followed_by_user;
    }

    public int getTotal_likes_received() {
        return total_likes_received;
    }

    public void setTotal_likes_received(int total_likes_received) {
        this.total_likes_received = total_likes_received;
    }

    public int getTotal_wordage() {
        return total_wordage;
    }

    public void setTotal_wordage(int total_wordage) {
        this.total_wordage = total_wordage;
    }
}
