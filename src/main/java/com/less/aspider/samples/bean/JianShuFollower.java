package com.less.aspider.samples.bean;

/**
 * Created by deeper on 2018/1/15.
 */

public class JianShuFollower {

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
