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

    public void createTable() {
        String sql = String.format("create table if not exists %s(id int primary key auto_increment, slug varchar(30) not null unique,nickname varchar(30), followers_count int,jsonText longtext)", tableName);
        DBHelper.execSQL(sql);
    }

    @Override
    public synchronized void save(JianShuUser entity) {
        String[] params = new String[] { entity.getSlug(), entity.getNickname(),entity.getFollowers_count() + "",entity.getJsonText()};
        String sql = String.format("insert into %s(slug,nickname,followers_count,jsonText) values(?,?,?,?)", tableName);
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
