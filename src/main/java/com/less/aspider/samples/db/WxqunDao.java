package com.less.aspider.samples.db;

import com.less.aspider.db.BaseDao;
import com.less.aspider.samples.bean.Wxqun;
import com.less.aspider.util.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * Created by deeper on 2018/2/6.
 */

public class WxqunDao extends BaseDao.SimpleDao<Wxqun> {

    private QueryRunner queryRunner = JdbcUtils.getQueryRunner("wxqun");

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
    public void save(Wxqun entity) {
        String[] params = new String[]{entity.getName(), entity.getDescption(), entity.getInfo_wx(), entity.getInfo_qq(), entity.getHotDegress(),
                entity.getType(), entity.getArea(), entity.getTime(), entity.getTags(), entity.getImg_head(), entity.getQrcode()};

        String sql = String.format("insert into %s" +
                "(name, descption, info_wx, info_qq, hotDegress, type, area, time, tags, img_head, qrcode)" +
                " values(?,?,?,?,?,?,?,?,?,?,?)", tableName);
        try {
            queryRunner.update(sql, params);
            System.out.println("save: " + entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String generateTableSQL() {
        String sql = String.format("create table if not exists %s" +
                "(" +
                "id int primary key auto_increment," +
                "name varchar(255)," +
                "descption text," +
                "info_wx varchar(100)," +
                "info_qq varchar(100)," +
                "hotDegress varchar(20)," +
                "type varchar(100)," +
                "area varchar(20)," +
                "time varchar(30)," +
                "tags varchar(255)," +
                "img_head varchar(255)," +
                "qrcode varchar(255) not null unique" +
                ")", tableName);
        return sql;
    }

    @Override
    public Class getEntityClass() {
        return Wxqun.class;
    }
}
