package com.less.aspider.samples.db;

import com.less.aspider.db.BaseDao;
import com.less.aspider.db.DBHelper;
import com.less.aspider.db.Result;
import com.less.aspider.samples.bean.JianShuUser;

import java.util.List;

/**
 * Created by deeper on 2018/1/14.
 */

public class JianshuDao implements BaseDao<JianShuUser> {

    private String tableName = getEntityClass().getSimpleName();

    @Override
    public void createTable() {
        String sql = String.format("create table if not exists %s" +
                "(" +
                "id int primary key auto_increment," +
                "slug varchar(30) not null unique," +
                "userid int," +
                "nickname varchar(30)," +
                "avatar tinytext," +
                "intro_compiled text," + // 个性签名
                "homepage varchar(255)," +
                "gender int," +
                "city varchar(50)," +
                "is_signed_author varchar(5)," + // 签约作者
                "badges_count int," + // 徽章数
                "total_likes_received int," + // 收到喜欢数
                "total_wordage int," + // 总字数
                "followers_count int," + // 粉丝数
                "following_count int," + // 关注数
                "notes_count int," + // 发表文章数
                "is_blocking_user varchar(5)," + // 小黑屋
                "subscriptions_count int," + // 关注专题数
                "liked_notes_count int," + // 关注喜欢数
                "email varchar(50)" +
                ")", tableName);
        DBHelper.execSQL(sql);
    }

    @Override
    public synchronized void save(JianShuUser entity) {
        String[] params = new String[]{entity.getSlug(),
                entity.getId() + "",
                entity.getNickname(),
                entity.getAvatar(),
                entity.getIntro_compiled(),
                entity.getHomepage(),
                entity.getGender() + "",
                String.valueOf(entity.getCity()),
                entity.isIs_signed_author() + "",
                entity.getBadges_count() + "",
                entity.getTotal_likes_received() + "",
                entity.getTotal_wordage() + "",
                entity.getFollowers_count() + "",
                entity.getFollowing_count() + "",
                entity.getNotes_count() + "",
                entity.isIs_blocking_user() + "",
                entity.getSubscriptions_count() + "",
                entity.getLiked_notes_count() + "",
                entity.getEmail()};
        String sql = String.format("insert into %s" +
                "(slug,userid,nickname,avatar,intro_compiled,homepage,gender,city,is_signed_author,badges_count,total_likes_received,total_wordage,followers_count,following_count,notes_count,is_blocking_user,subscriptions_count,liked_notes_count,email)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", tableName);
        DBHelper.execSQL(sql, params);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(JianShuUser entity) {

    }

    @Override
    public void saveOrUpdate(JianShuUser entity) {

    }

    @Override
    public JianShuUser getById(Long id) {
        return null;
    }

    @Override
    public List<JianShuUser> findAll() {
        return null;
    }

    @Override
    public Class getEntityClass() {
        return JianShuUser.class;
    }

    @Override
    public Result<JianShuUser> getPage(int currentPage) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<JianShuUser> search(String text) {
        return null;
    }
}
