package com.less.aspider.samples.bean;

import java.util.List;

/**
 * Created by deeper on 2018/2/15.
 */

public class ZhihuFollowees {

    /**
     * paging : {"is_end":false,"next":"https://api.zhihu.com/people/a59ace4fb5b502e364e54f84026aa951/followees?limit=20&offset=100","previous":"https://api.zhihu.com/people/a59ace4fb5b502e364e54f84026aa951/followees?limit=20&offset=60"}
     * data : [{"is_followed":false,"badge":[],"name":"代码家","url":"https://api.zhihu.com/people/f37021d8a7144874b70c1c78b02d6c89","gender":1,"user_type":"people","answer_count":26,"headline":"人生几十年，开心都嫌短","avatar_url":"http://pic4.zhimg.com/50/v2-82767fc516e07796f4acb171f28047f8_s.jpg","is_following":true,"follower_count":5555,"type":"people","id":"f37021d8a7144874b70c1c78b02d6c89","question_count":0,"voteup_count":2023},{"is_followed":false,"badge":[],"name":"白起","url":"https://api.zhihu.com/people/8da5e5ee5fb663861853357135e453d0","gender":1,"user_type":"people","answer_count":1901,"headline":"围棋/游戏开发/象棋/web/创业/少儿教育","avatar_url":"http://pic2.zhimg.com/50/2de69c6da5e7da1183644d0c1408d6bf_s.jpg","is_following":true,"follower_count":5738,"type":"people","id":"8da5e5ee5fb663861853357135e453d0","question_count":19,"voteup_count":17400},{"is_followed":false,"badge":[],"name":"Lightwing","url":"https://api.zhihu.com/people/b1a3919d6cf4f505dd6f41f09c028437","gender":1,"user_type":"people","answer_count":359,"headline":"公众号：Lightwing 微博：李轶睿","avatar_url":"http://pic3.zhimg.com/50/v2-642cb8ee6edfa2f7cc26011d3d9a96f7_s.jpg","is_following":true,"follower_count":231334,"type":"people","id":"b1a3919d6cf4f505dd6f41f09c028437","question_count":100,"voteup_count":504260},{"is_followed":false,"badge":[{"type":"identity","description":"已认证的官方帐号"}],"name":"腾讯Bugly","url":"https://api.zhihu.com/people/c944fc82a6fa33dedb315f86cfd4eff7","gender":1,"user_type":"organization","answer_count":8,"headline":"一种愉悦的开发方式","avatar_url":"http://pic4.zhimg.com/50/70a08c690f7313464792663ea399d656_s.jpg","is_following":true,"follower_count":16340,"type":"people","id":"c944fc82a6fa33dedb315f86cfd4eff7","question_count":0,"voteup_count":10857},{"is_followed":false,"badge":[],"name":"Jack wang","url":"https://api.zhihu.com/people/9ff93d7e625e910a8031bd5a29c3c82e","gender":0,"user_type":"people","answer_count":0,"headline":"编程Java","avatar_url":"http://pic2.zhimg.com/50/7531b7bbcbbfd68f2699c7d7f446e034_s.jpg","is_following":true,"follower_count":1,"type":"people","id":"9ff93d7e625e910a8031bd5a29c3c82e","question_count":0,"voteup_count":0},{"is_followed":false,"badge":[],"name":"廖祜秋","url":"https://api.zhihu.com/people/0c8ad86441eed31000868f3eb8372a5c","gender":1,"user_type":"people","answer_count":22,"headline":"工程师。","avatar_url":"http://pic1.zhimg.com/50/77137a0ab_s.jpg","is_following":true,"follower_count":1589,"type":"people","id":"0c8ad86441eed31000868f3eb8372a5c","question_count":4,"voteup_count":1734},{"is_followed":false,"badge":[],"name":"闭关写代码","url":"https://api.zhihu.com/people/3fe9c07261beef166bad595ba712c536","gender":1,"user_type":"people","answer_count":16,"headline":"Android Developer","avatar_url":"http://pic1.zhimg.com/50/v2-4bb8753e5c3d5f4e75d4337521347b00_s.jpg","is_following":true,"follower_count":1560,"type":"people","id":"3fe9c07261beef166bad595ba712c536","question_count":1,"voteup_count":12166},{"is_followed":false,"badge":[],"name":"Hmily","url":"https://api.zhihu.com/people/632e95e5ac4e78fbb1c27558eda4cee6","gender":1,"user_type":"people","answer_count":1,"headline":"吾爱破解论坛创始人 www.52pojie.cn","avatar_url":"http://pic4.zhimg.com/50/0f03f429e_s.jpg","is_following":true,"follower_count":318,"type":"people","id":"632e95e5ac4e78fbb1c27558eda4cee6","question_count":0,"voteup_count":9}]
     */

    private PagingBean paging;
    private List<DataBean> data;

    public PagingBean getPaging() {
        return paging;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PagingBean {
        /**
         * is_end : false
         * next : https://api.zhihu.com/people/a59ace4fb5b502e364e54f84026aa951/followees?limit=20&offset=100
         * previous : https://api.zhihu.com/people/a59ace4fb5b502e364e54f84026aa951/followees?limit=20&offset=60
         */

        private boolean is_end;
        private String next;
        private String previous;

        public boolean isIs_end() {
            return is_end;
        }

        public void setIs_end(boolean is_end) {
            this.is_end = is_end;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }
    }

    public static class DataBean {
        /**
         * is_followed : false
         * badge : []
         * name : 代码家
         * url : https://api.zhihu.com/people/f37021d8a7144874b70c1c78b02d6c89
         * gender : 1
         * user_type : people
         * answer_count : 26
         * headline : 人生几十年，开心都嫌短
         * avatar_url : http://pic4.zhimg.com/50/v2-82767fc516e07796f4acb171f28047f8_s.jpg
         * is_following : true
         * follower_count : 5555
         * type : people
         * id : f37021d8a7144874b70c1c78b02d6c89
         * question_count : 0
         * voteup_count : 2023
         */

        private boolean is_followed;
        private String name;
        private String url;
        private int gender;
        private String user_type;
        private int answer_count;
        private String headline;
        private String avatar_url;
        private boolean is_following;
        private int follower_count;
        private String type;
        private String id;
        private int question_count;
        private int voteup_count;
        private List<?> badge;

        public boolean isIs_followed() {
            return is_followed;
        }

        public void setIs_followed(boolean is_followed) {
            this.is_followed = is_followed;
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

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public int getAnswer_count() {
            return answer_count;
        }

        public void setAnswer_count(int answer_count) {
            this.answer_count = answer_count;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public boolean isIs_following() {
            return is_following;
        }

        public void setIs_following(boolean is_following) {
            this.is_following = is_following;
        }

        public int getFollower_count() {
            return follower_count;
        }

        public void setFollower_count(int follower_count) {
            this.follower_count = follower_count;
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

        public int getQuestion_count() {
            return question_count;
        }

        public void setQuestion_count(int question_count) {
            this.question_count = question_count;
        }

        public int getVoteup_count() {
            return voteup_count;
        }

        public void setVoteup_count(int voteup_count) {
            this.voteup_count = voteup_count;
        }

        public List<?> getBadge() {
            return badge;
        }

        public void setBadge(List<?> badge) {
            this.badge = badge;
        }
    }
}
