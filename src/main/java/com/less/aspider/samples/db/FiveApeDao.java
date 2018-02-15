package com.less.aspider.samples.db;

import com.less.aspider.db.BaseDao;
import com.less.aspider.samples.bean.Ape;
import com.less.aspider.util.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @author deeper
 * @date 2018/1/14
 */

public class FiveApeDao extends BaseDao.SimpleDao<Ape> {

    private QueryRunner queryRunner = JdbcUtils.getQueryRunner("51ape_new");

    @Override
    public void createTable() {
        try {
            String sql = generateTableSQL();
            queryRunner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Ape entity) {
        String[] params = new String[] { entity.getName(), entity.getAlbum(),entity.getKbps(),entity.getSize(),entity.getUrl(),entity.getPassword()};
        String sql = String.format("insert into %s(name,album,kbps,size,url,password) values(?,?,?,?,?,?)", tableName);
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class getEntityClass() {
        return Ape.class;
    }

    @Override
    protected String generateTableSQL() {
        String sql = String.format("create table if not exists %s(id int primary key auto_increment, name varchar(100),album varchar(100), kbps varchar(20),size varchar(20),url varchar(100) not null unique,password varchar(30))", tableName);
        return sql;
    }
}
