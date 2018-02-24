package com.less.aspider.samples.bean;

import com.less.aspider.dao.Column;
import com.less.aspider.dao.Table;

import java.util.List;

/**
 * Created by deeper on 2018/2/15.
 */

@Table
public class ZhihuPerson {

    /**
     * is_followed : false
     * following_count : 0
     * is_hanged : false
     * shared_count : 0
     * user_type : organization
     * is_unicom_free : false
     * pins_count : 0
     * is_following : true
     * marked_answers_text :
     * is_activity_blocked : false
     * type : people
     * infinity : {"is_activated":false,"url":"","show_my_infinity_entrance":false}
     * is_force_renamed : false
     * id : c11d407c53eabd5f698584e48f792a2c
     * favorite_count : 0
     * voteup_count : 0
     * live_count : 0
     * is_blocking : false
     * following_columns_count : 0
     * is_baned : false
     * headline : 认真你就输啦 (・ω・)ノ
     * url_token : acfundan-mu-shi-pin-wang
     * participated_live_count : 0
     * following_favlists_count : 0
     * is_bind_sina : false
     * favorited_count : 0
     * open_ebook_feature : true
     * follower_count : 0
     * badge : [{"type":"identity","description":"已认证的官方帐号"}]
     * following_topic_count : 0
     * description : 当前为机构帐号主页，需升级客户端版本才能正常访问。

     大家好，我是A站～～～
     一个被玩坏的机构账号！
     小编性别不详，年龄嘛，大家都是几十岁的人了，别问啦~
     欢迎大家各种提问，反正我也会假装看不到！
     认真你就输了！欢迎大家来我们破站吹水！
     * business : {"introduction":"","avatar_url":"https://pic3.zhimg.com/e82bab09c_s.jpg","name":"","experience ":"","url":"","excerpt":"","type":"topic","id":""}
     * columns_count : 1
     * answer_count : 0
     * cover_url : https://pic3.zhimg.com/v2-ddb75f0e09a0abb7afbaf751f4d550ed_r.jpg
     * org_detail : {"organization_name":"北京弹幕网络科技有限公司","home_page":"http://www.acfun.cn","industry":"互联网","is_verified":true}
     * question_count : 0
     * articles_count : 0
     * name : AcFun弹幕视频网
     * url : https://api.zhihu.com/people/c11d407c53eabd5f698584e48f792a2c
     * gender : 1
     * is_locked : false
     * avatar_url : http://pic1.zhimg.com/50/v2-216ff35c92d96448a96970b14a0afc69_s.jpg
     * following_question_count : 0
     * thanked_count : 0
     * hosted_live_count : 0
     * independent_articles_count : 0
     */

    private boolean is_followed;
    @Column
    private int following_count;
    private boolean is_hanged;
    @Column
    private int shared_count;
    @Column
    private String user_type;
    private boolean is_unicom_free;
    private int pins_count;
    private boolean is_following;
    private String marked_answers_text;
    private boolean is_activity_blocked;
    @Column
    private String type;
    private InfinityBean infinity;
    private boolean is_force_renamed;
    @Column
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
    private boolean is_baned;
    @Column
    private String headline;
    @Column
    private String url_token;
    private int participated_live_count;
    private int following_favlists_count;
    private boolean is_bind_sina;
    @Column
    private int favorited_count;
    private boolean open_ebook_feature;
    @Column
    private int follower_count;
    @Column
    private int following_topic_count;
    @Column
    private String description;
    private BusinessBean business;
    private int columns_count;
    @Column
    private int answer_count;
    @Column
    private String cover_url;
    private OrgDetailBean org_detail;
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
    private boolean is_locked;
    @Column
    private String avatar_url;
    @Column
    private int following_question_count;
    @Column
    private int thanked_count;
    private int hosted_live_count;
    private int independent_articles_count;
    private List<BadgeBean> badge;

    public boolean isIs_followed() {
        return is_followed;
    }

    public void setIs_followed(boolean is_followed) {
        this.is_followed = is_followed;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public boolean isIs_hanged() {
        return is_hanged;
    }

    public void setIs_hanged(boolean is_hanged) {
        this.is_hanged = is_hanged;
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

    public boolean isIs_unicom_free() {
        return is_unicom_free;
    }

    public void setIs_unicom_free(boolean is_unicom_free) {
        this.is_unicom_free = is_unicom_free;
    }

    public int getPins_count() {
        return pins_count;
    }

    public void setPins_count(int pins_count) {
        this.pins_count = pins_count;
    }

    public boolean isIs_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }

    public String getMarked_answers_text() {
        return marked_answers_text;
    }

    public void setMarked_answers_text(String marked_answers_text) {
        this.marked_answers_text = marked_answers_text;
    }

    public boolean isIs_activity_blocked() {
        return is_activity_blocked;
    }

    public void setIs_activity_blocked(boolean is_activity_blocked) {
        this.is_activity_blocked = is_activity_blocked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InfinityBean getInfinity() {
        return infinity;
    }

    public void setInfinity(InfinityBean infinity) {
        this.infinity = infinity;
    }

    public boolean isIs_force_renamed() {
        return is_force_renamed;
    }

    public void setIs_force_renamed(boolean is_force_renamed) {
        this.is_force_renamed = is_force_renamed;
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

    public boolean isIs_blocking() {
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

    public boolean isIs_baned() {
        return is_baned;
    }

    public void setIs_baned(boolean is_baned) {
        this.is_baned = is_baned;
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

    public int getParticipated_live_count() {
        return participated_live_count;
    }

    public void setParticipated_live_count(int participated_live_count) {
        this.participated_live_count = participated_live_count;
    }

    public int getFollowing_favlists_count() {
        return following_favlists_count;
    }

    public void setFollowing_favlists_count(int following_favlists_count) {
        this.following_favlists_count = following_favlists_count;
    }

    public boolean isIs_bind_sina() {
        return is_bind_sina;
    }

    public void setIs_bind_sina(boolean is_bind_sina) {
        this.is_bind_sina = is_bind_sina;
    }

    public int getFavorited_count() {
        return favorited_count;
    }

    public void setFavorited_count(int favorited_count) {
        this.favorited_count = favorited_count;
    }

    public boolean isOpen_ebook_feature() {
        return open_ebook_feature;
    }

    public void setOpen_ebook_feature(boolean open_ebook_feature) {
        this.open_ebook_feature = open_ebook_feature;
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

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    public int getColumns_count() {
        return columns_count;
    }

    public void setColumns_count(int columns_count) {
        this.columns_count = columns_count;
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

    public OrgDetailBean getOrg_detail() {
        return org_detail;
    }

    public void setOrg_detail(OrgDetailBean org_detail) {
        this.org_detail = org_detail;
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

    public boolean isIs_locked() {
        return is_locked;
    }

    public void setIs_locked(boolean is_locked) {
        this.is_locked = is_locked;
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

    public int getHosted_live_count() {
        return hosted_live_count;
    }

    public void setHosted_live_count(int hosted_live_count) {
        this.hosted_live_count = hosted_live_count;
    }

    public int getIndependent_articles_count() {
        return independent_articles_count;
    }

    public void setIndependent_articles_count(int independent_articles_count) {
        this.independent_articles_count = independent_articles_count;
    }

    public List<BadgeBean> getBadge() {
        return badge;
    }

    public void setBadge(List<BadgeBean> badge) {
        this.badge = badge;
    }

    public static class InfinityBean {
        /**
         * is_activated : false
         * url :
         * show_my_infinity_entrance : false
         */

        private boolean is_activated;
        private String url;
        private boolean show_my_infinity_entrance;

        public boolean isIs_activated() {
            return is_activated;
        }

        public void setIs_activated(boolean is_activated) {
            this.is_activated = is_activated;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isShow_my_infinity_entrance() {
            return show_my_infinity_entrance;
        }

        public void setShow_my_infinity_entrance(boolean show_my_infinity_entrance) {
            this.show_my_infinity_entrance = show_my_infinity_entrance;
        }
    }

    public static class BusinessBean {
        /**
         * introduction :
         * avatar_url : https://pic3.zhimg.com/e82bab09c_s.jpg
         * name :
         * experience  :
         * url :
         * excerpt :
         * type : topic
         * id :
         */

        private String introduction;
        private String avatar_url;
        private String name;
        private String experience;
        private String url;
        private String excerpt;
        private String type;
        private String id;

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
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
    }

    public static class OrgDetailBean {
        /**
         * organization_name : 北京弹幕网络科技有限公司
         * home_page : http://www.acfun.cn
         * industry : 互联网
         * is_verified : true
         */

        private String organization_name;
        private String home_page;
        private String industry;
        private boolean is_verified;

        public String getOrganization_name() {
            return organization_name;
        }

        public void setOrganization_name(String organization_name) {
            this.organization_name = organization_name;
        }

        public String getHome_page() {
            return home_page;
        }

        public void setHome_page(String home_page) {
            this.home_page = home_page;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public boolean isIs_verified() {
            return is_verified;
        }

        public void setIs_verified(boolean is_verified) {
            this.is_verified = is_verified;
        }
    }

    public static class BadgeBean {
        /**
         * type : identity
         * description : 已认证的官方帐号
         */

        private String type;
        private String description;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
