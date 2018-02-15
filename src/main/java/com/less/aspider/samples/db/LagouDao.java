package com.less.aspider.samples.db;

import com.less.aspider.db.BaseDao;
import com.less.aspider.samples.bean.Lagou;
import com.less.aspider.util.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by deeper on 2018/2/6.
 */

public class LagouDao extends BaseDao.SimpleDao<Lagou> {

    private QueryRunner queryRunner = JdbcUtils.getQueryRunner("lagou");

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
    public void save(Lagou entity) {
        List<Lagou.ContentBean.DataBean.PageBean.ResultBean> list = entity.getContent().getData().getPage().getResult();
        for (Lagou.ContentBean.DataBean.PageBean.ResultBean result : list) {
            String[] params = new String[]{String.valueOf(result.getPositionId()),result.getPositionName(), result.getCity(), result.getCreateTime(), result.getSalary(), String.valueOf(result.getCompanyId()), "https://www.lagou.com/" + result.getCompanyLogo(), result.getCompanyName(), result.getCompanyFullName()};
            String sql = String.format("insert into %s" +
                    "(positionId, positionName, city,createTime,salary,companyId,companyLogo,companyName,companyFullName)" +
                    " values(?,?,?,?,?,?,?,?,?)", tableName);
            try {
                queryRunner.update(sql, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected String generateTableSQL() {
        String sql = String.format("create table if not exists %s" +
                "(" +
                "id int primary key auto_increment," +
                "positionId varchar(30) not null unique," +
                "positionName varchar(100)," +
                "city varchar(30)," +
                "createTime varchar(50)," +
                "salary varchar(30)," +
                "companyId varchar(30)," +
                "companyLogo varchar(255)," +
                "companyName varchar(100)," +
                "companyFullName varchar(255)" +
                ")", tableName);
        return sql;
    }

    @Override
    public Class getEntityClass() {
        return Lagou.class;
    }
}
