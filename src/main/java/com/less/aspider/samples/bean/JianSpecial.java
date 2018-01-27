package com.less.aspider.samples.bean;

import java.util.List;

/**
 *
 * @author deeper
 * @date 2018/1/18
 */

public class JianSpecial {

    private int id;
    private String title;
    private String slug;
    private String description;
    private String image;
    private OwnerBean owner;
    private int coeditors_count;
    private int recommended_users_count;
    private boolean is_current_user_editor;
    private boolean enable_subscription_push;
    private boolean is_subscribed;
    private int notes_count;
    private int subscribers_count;
    private boolean can_contribute;
    private boolean audit_contribute;
    private int last_responded_at;
    private int average_response_time;
    private List<?> tags;
    private List<?> coeditors;
    private String jsonText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public int getCoeditors_count() {
        return coeditors_count;
    }

    public void setCoeditors_count(int coeditors_count) {
        this.coeditors_count = coeditors_count;
    }

    public int getRecommended_users_count() {
        return recommended_users_count;
    }

    public void setRecommended_users_count(int recommended_users_count) {
        this.recommended_users_count = recommended_users_count;
    }

    public boolean isIs_current_user_editor() {
        return is_current_user_editor;
    }

    public void setIs_current_user_editor(boolean is_current_user_editor) {
        this.is_current_user_editor = is_current_user_editor;
    }

    public boolean isEnable_subscription_push() {
        return enable_subscription_push;
    }

    public void setEnable_subscription_push(boolean enable_subscription_push) {
        this.enable_subscription_push = enable_subscription_push;
    }

    public boolean isIs_subscribed() {
        return is_subscribed;
    }

    public void setIs_subscribed(boolean is_subscribed) {
        this.is_subscribed = is_subscribed;
    }

    public int getNotes_count() {
        return notes_count;
    }

    public void setNotes_count(int notes_count) {
        this.notes_count = notes_count;
    }

    public int getSubscribers_count() {
        return subscribers_count;
    }

    public void setSubscribers_count(int subscribers_count) {
        this.subscribers_count = subscribers_count;
    }

    public boolean isCan_contribute() {
        return can_contribute;
    }

    public void setCan_contribute(boolean can_contribute) {
        this.can_contribute = can_contribute;
    }

    public boolean isAudit_contribute() {
        return audit_contribute;
    }

    public void setAudit_contribute(boolean audit_contribute) {
        this.audit_contribute = audit_contribute;
    }

    public int getLast_responded_at() {
        return last_responded_at;
    }

    public void setLast_responded_at(int last_responded_at) {
        this.last_responded_at = last_responded_at;
    }

    public int getAverage_response_time() {
        return average_response_time;
    }

    public void setAverage_response_time(int average_response_time) {
        this.average_response_time = average_response_time;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    public List<?> getCoeditors() {
        return coeditors;
    }

    public void setCoeditors(List<?> coeditors) {
        this.coeditors = coeditors;
    }

    public static class OwnerBean {

        private int id;
        private String nickname;
        private String slug;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }
}
