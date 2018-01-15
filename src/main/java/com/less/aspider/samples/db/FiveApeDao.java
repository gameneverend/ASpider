package com.less.aspider.samples.db;

import com.less.aspider.db.BaseDao;
import com.less.aspider.db.DBHelper;
import com.less.aspider.db.Result;
import com.less.aspider.samples.bean.Ape;

import java.util.List;

/**
 * Created by deeper on 2018/1/14.
 */

public class FiveApeDao implements BaseDao<Ape> {

    private String tableName = getEntityClass().getSimpleName();

    public void createTable() {
        String sql = String.format("create table if not exists %s(id int primary key auto_increment, name varchar(100),album varchar(100), kbps varchar(20),size varchar(20),url varchar(100) not null unique,password varchar(30))", tableName);
        DBHelper.execSQL(sql);
    }

    @Override
    public synchronized void save(Ape entity) {
        String[] params = new String[] { entity.getName(), entity.getAlbum(),entity.getKbps(),entity.getSize(),entity.getUrl(),entity.getPassword()};
        String sql = String.format("insert into %s(name,album,kbps,size,url,password) values(?,?,?,?,?,?)", tableName);
        DBHelper.execSQL(sql, params);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Ape entity) {

    }

    @Override
    public void saveOrUpdate(Ape entity) {

    }

    @Override
    public Ape getById(Long id) {
        return null;
    }

    @Override
    public List<Ape> findAll() {
        return null;
    }

    @Override
    public Class getEntityClass() {
        return Ape.class;
    }

    @Override
    public Result<Ape> getPage(int currentPage) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<Ape> search(String text) {
        return null;
    }
}
