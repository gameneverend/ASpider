package com.less.aspider.samples.db;

import com.less.aspider.dao.SimpleDao;
import com.less.aspider.db.DBHelper;
import com.less.aspider.samples.bean.JianSpecial;

/**
 * Created by deeper on 2018/1/19.
 */

public class JianSpecialDao extends SimpleDao<JianSpecial> {

    @Override
    public synchronized void save(JianSpecial entity) {
        String[] params = new String[]{entity.getId() + "", entity.getTitle(), entity.getSlug(),entity.getDescription(), entity.getImage(), entity.getTags().toString(), entity.getNotes_count() + "", entity.getSubscribers_count() + "", entity.getOwner().getSlug(), entity.getJsonText()};
        String sql = String.format("insert into %s(specialId, title, slug, description, image, tags, notes_count, subscribers_count, owner_slug, jsonText) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", tableName);
        DBHelper.execSQL(sql, params);
    }

    @Override
    protected String generateTableSQL() {
        String tableSQL = String.format("create table if not exists %s(id int primary key auto_increment, specialId varchar(30),title varchar(150),slug varchar(50) not null unique, description text,image varchar(100),tags varchar(200),notes_count varchar(20),subscribers_count varchar(20),owner_slug varchar(20),jsonText text)",tableName);
        return tableSQL;
    }

    @Override
    public Class getEntityClass() {
        return JianSpecial.class;
    }
}
