package com.less.aspider.samples.bean;

import java.util.List;

/**
 * Created by deeper on 2018/1/5.
 */

public class JianShuUser {

    /**
     * id : 1059531
     * email : zhangshuyue@qq.com
     * nickname : 张书乐
     * slug : 891903dd4b3b
     * avatar : http://upload.jianshu.io/users/upload_avatars/1059531/a887446e35b6.JPG
     * background_image : null
     * intro_compiled : TMT名博。人民网、人民邮电报专栏作者。出版有《实战网络营销》《越界：互联网+时代必先搞懂的大败局》等著作。
     * homepage : http://www.weibo.com/zhangshuyue
     * gender : 1
     * city : null
     * is_signed_author : false
     * sns_nicknames : {"weibo":"张书乐"}
     * public_snses : [{"id":287715,"name":"weibo","nickname":"张书乐","homepage":"http://weibo.com/u/1402093312","is_public":true},{"id":287716,"name":"wechat","nickname":"张书乐","homepage":"","is_public":true},{"id":399011,"name":"douban","nickname":"张书乐","homepage":"http://www.douban.com/people/78746822","is_public":true}]
     * badges : [{"custom_info":null,"text":"简书游戏优秀作者","intro_url":"http://www.jianshu.com/p/d1d89ed69098","image_url":"http://upload.jianshu.io/user_badge/4c3e27d0-8aa4-4c52-b676-bb8697254f02","icon":9}]
     * badges_count : 1
     * is_following_user : false
     * is_followed_by_user : false
     * total_likes_received : 1337
     * total_wordage : 953170
     * subscriptions_count : 2
     * followers_count : 6088
     * notes_count : 531
     * following_count : 19
     * bookmarks_count : 2
     * is_blocking_user : false
     * notebooks_count : 1
     * owned_collections_count : 4
     * editable_collections_count : 4
     * liked_notes_count : 3
     * subscribing_notebooks_count : 0
     * subscribing_collections_count : 2
     * activities_count : 0
     * work_exps : []
     * educations : []
     */

    private int id;
    private String email;
    private String nickname;
    private String slug;
    private String avatar;
    private Object background_image;
    private String intro_compiled;
    private String homepage;
    private int gender;
    private Object city;
    private boolean is_signed_author;
    private SnsNicknamesBean sns_nicknames;
    private int badges_count;
    private boolean is_following_user;
    private boolean is_followed_by_user;
    private int total_likes_received;
    private int total_wordage;
    private int subscriptions_count;
    private int followers_count;
    private int notes_count;
    private int following_count;
    private int bookmarks_count;
    private boolean is_blocking_user;
    private int notebooks_count;
    private int owned_collections_count;
    private int editable_collections_count;
    private int liked_notes_count;
    private int subscribing_notebooks_count;
    private int subscribing_collections_count;
    private int activities_count;
    private List<PublicSnsesBean> public_snses;
    private List<BadgesBean> badges;
    private List<?> work_exps;
    private List<?> educations;

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

    public Object getBackground_image() {
        return background_image;
    }

    public void setBackground_image(Object background_image) {
        this.background_image = background_image;
    }

    public String getIntro_compiled() {
        return intro_compiled;
    }

    public void setIntro_compiled(String intro_compiled) {
        this.intro_compiled = intro_compiled;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public boolean isIs_signed_author() {
        return is_signed_author;
    }

    public void setIs_signed_author(boolean is_signed_author) {
        this.is_signed_author = is_signed_author;
    }

    public SnsNicknamesBean getSns_nicknames() {
        return sns_nicknames;
    }

    public void setSns_nicknames(SnsNicknamesBean sns_nicknames) {
        this.sns_nicknames = sns_nicknames;
    }

    public int getBadges_count() {
        return badges_count;
    }

    public void setBadges_count(int badges_count) {
        this.badges_count = badges_count;
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

    public int getSubscriptions_count() {
        return subscriptions_count;
    }

    public void setSubscriptions_count(int subscriptions_count) {
        this.subscriptions_count = subscriptions_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getNotes_count() {
        return notes_count;
    }

    public void setNotes_count(int notes_count) {
        this.notes_count = notes_count;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public int getBookmarks_count() {
        return bookmarks_count;
    }

    public void setBookmarks_count(int bookmarks_count) {
        this.bookmarks_count = bookmarks_count;
    }

    public boolean isIs_blocking_user() {
        return is_blocking_user;
    }

    public void setIs_blocking_user(boolean is_blocking_user) {
        this.is_blocking_user = is_blocking_user;
    }

    public int getNotebooks_count() {
        return notebooks_count;
    }

    public void setNotebooks_count(int notebooks_count) {
        this.notebooks_count = notebooks_count;
    }

    public int getOwned_collections_count() {
        return owned_collections_count;
    }

    public void setOwned_collections_count(int owned_collections_count) {
        this.owned_collections_count = owned_collections_count;
    }

    public int getEditable_collections_count() {
        return editable_collections_count;
    }

    public void setEditable_collections_count(int editable_collections_count) {
        this.editable_collections_count = editable_collections_count;
    }

    public int getLiked_notes_count() {
        return liked_notes_count;
    }

    public void setLiked_notes_count(int liked_notes_count) {
        this.liked_notes_count = liked_notes_count;
    }

    public int getSubscribing_notebooks_count() {
        return subscribing_notebooks_count;
    }

    public void setSubscribing_notebooks_count(int subscribing_notebooks_count) {
        this.subscribing_notebooks_count = subscribing_notebooks_count;
    }

    public int getSubscribing_collections_count() {
        return subscribing_collections_count;
    }

    public void setSubscribing_collections_count(int subscribing_collections_count) {
        this.subscribing_collections_count = subscribing_collections_count;
    }

    public int getActivities_count() {
        return activities_count;
    }

    public void setActivities_count(int activities_count) {
        this.activities_count = activities_count;
    }

    public List<PublicSnsesBean> getPublic_snses() {
        return public_snses;
    }

    public void setPublic_snses(List<PublicSnsesBean> public_snses) {
        this.public_snses = public_snses;
    }

    public List<BadgesBean> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgesBean> badges) {
        this.badges = badges;
    }

    public List<?> getWork_exps() {
        return work_exps;
    }

    public void setWork_exps(List<?> work_exps) {
        this.work_exps = work_exps;
    }

    public List<?> getEducations() {
        return educations;
    }

    public void setEducations(List<?> educations) {
        this.educations = educations;
    }

    public static class SnsNicknamesBean {
        /**
         * weibo : 张书乐
         */

        private String weibo;

        public String getWeibo() {
            return weibo;
        }

        public void setWeibo(String weibo) {
            this.weibo = weibo;
        }
    }

    public static class PublicSnsesBean {
        /**
         * id : 287715
         * name : weibo
         * nickname : 张书乐
         * homepage : http://weibo.com/u/1402093312
         * is_public : true
         */

        private int id;
        private String name;
        private String nickname;
        private String homepage;
        private boolean is_public;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        public boolean isIs_public() {
            return is_public;
        }

        public void setIs_public(boolean is_public) {
            this.is_public = is_public;
        }
    }

    public static class BadgesBean {
        /**
         * custom_info : null
         * text : 简书游戏优秀作者
         * intro_url : http://www.jianshu.com/p/d1d89ed69098
         * image_url : http://upload.jianshu.io/user_badge/4c3e27d0-8aa4-4c52-b676-bb8697254f02
         * icon : 9
         */

        private Object custom_info;
        private String text;
        private String intro_url;
        private String image_url;
        private int icon;

        public Object getCustom_info() {
            return custom_info;
        }

        public void setCustom_info(Object custom_info) {
            this.custom_info = custom_info;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIntro_url() {
            return intro_url;
        }

        public void setIntro_url(String intro_url) {
            this.intro_url = intro_url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }
    }
}
