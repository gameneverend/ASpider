package com.less.aspider.samples.db;

import com.less.aspider.dao.SimpleDao;
import com.less.aspider.db.DBHelper;
import com.less.aspider.samples.bean.JianSubscriber;

/**
 *
 * @author deeper
 * @date 2018/1/19
 */

public class JianSubscriberDao extends SimpleDao<JianSubscriber> {

    @Override
    protected String generateTableSQL() {
        String tableSQL = String.format("create table if not exists %s(id int primary key auto_increment, userId varchar(30),slug varchar(50) not null, nickname varchar(250),avatar varchar(250),specialId varchar(50) )",tableName);
        return tableSQL;
    }

    @Override
    public synchronized void save(JianSubscriber entity) {
        String[] params = new String[]{entity.getId() + "", entity.getSlug(), entity.getNickname(), entity.getAvatar(), entity.getSpecialId()};
        String sql = String.format("insert into %s(userId, slug, nickname, avatar, specialId) values(?, ?, ?, ?, ?)", tableName);
        DBHelper.execSQL(sql, params);
    }

    @Override
    public Class getEntityClass() {
        return JianSubscriber.class;
    }
}
