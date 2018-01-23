package com.less.aspider.test;

import com.less.aspider.util.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * Created by deeper on 2018/1/23.
 */

public class TestDao {

    QueryRunner qr = JdbcUtils.getQueryRunner("test_c3p0");

    public void add(Person person) throws SQLException {
        String sql =" INSERT person(name,age) VALUES(?,?)";
        qr.execute(sql, person.getName(),person.getAge());
        System.out.println("add " + person.getName());
    }

    public void createTable() throws SQLException{
        String sql = "create table if not exists person(id INT PRIMARY KEY AUTO_INCREMENT,name varchar(20),age varchar(20) default '0')";
        qr.execute(sql);
    }

    public static void main(String[] args) throws SQLException {
        TestDao testDao = new TestDao();
        testDao.createTable();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            testDao.add(new Person("name " + i, "age " + i));
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - begin));
    }
}
