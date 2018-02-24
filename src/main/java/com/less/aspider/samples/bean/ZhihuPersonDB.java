package com.less.aspider.samples.bean;

import com.less.aspider.dao.Column;
import com.less.aspider.dao.Table;

@Table
public class ZhihuPersonDB {

    @Column
    private int following_count;
    @Column
    private int shared_count;
    @Column
    private String user_type;
    @Column
    private String type;
    @Column(columnDefinition = "varchar(255) primary key")
    private String id;
    @Column
    private int favorite_count;
    @Column
    private int voteup_count;
    @Column
    private int live_count;
    @Column
    private boolean is_blocking;
    @Column
    private int following_columns_count;
    @Column
    private String headline;
    @Column
    private String url_token;
    @Column
    private int favorited_count;
    @Column
    private int follower_count;
    @Column
    private int following_topic_count;
    @Column(columnDefinition = "varchar(2000)")
    private String description;
    @Column
    private int answer_count;
    @Column
    private String cover_url;
    @Column
    private int question_count;
    @Column
    private int articles_count;
    @Column
    private String name;
    @Column
    private String url;
    @Column
    private int gender;
    @Column
    private String avatar_url;
    @Column
    private int following_question_count;
    @Column
    private int thanked_count;

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public int getShared_count() {
        return shared_count;
    }

    public void setShared_count(int shared_count) {
        this.shared_count = shared_count;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public int getVoteup_count() {
        return voteup_count;
    }

    public void setVoteup_count(int voteup_count) {
        this.voteup_count = voteup_count;
    }

    public int getLive_count() {
        return live_count;
    }

    public void setLive_count(int live_count) {
        this.live_count = live_count;
    }

    public boolean is_blocking() {
        return is_blocking;
    }

    public void setIs_blocking(boolean is_blocking) {
        this.is_blocking = is_blocking;
    }

    public int getFollowing_columns_count() {
        return following_columns_count;
    }

    public void setFollowing_columns_count(int following_columns_count) {
        this.following_columns_count = following_columns_count;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getUrl_token() {
        return url_token;
    }

    public void setUrl_token(String url_token) {
        this.url_token = url_token;
    }

    public int getFavorited_count() {
        return favorited_count;
    }

    public void setFavorited_count(int favorited_count) {
        this.favorited_count = favorited_count;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    public int getFollowing_topic_count() {
        return following_topic_count;
    }

    public void setFollowing_topic_count(int following_topic_count) {
        this.following_topic_count = following_topic_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public int getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(int question_count) {
        this.question_count = question_count;
    }

    public int getArticles_count() {
        return articles_count;
    }

    public void setArticles_count(int articles_count) {
        this.articles_count = articles_count;
    }

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getFollowing_question_count() {
        return following_question_count;
    }

    public void setFollowing_question_count(int following_question_count) {
        this.following_question_count = following_question_count;
    }

    public int getThanked_count() {
        return thanked_count;
    }

    public void setThanked_count(int thanked_count) {
        this.thanked_count = thanked_count;
    }
}
