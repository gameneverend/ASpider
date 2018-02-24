package com.less.aspider.dao;

import com.less.aspider.util.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by deeper on 2018/2/14.
 */

public abstract class DefaultDataAccessor implements DataAccessor {

    private QueryRunner queryRunner = JdbcUtils.getQueryRunner(getDatabase());

    protected abstract String getDatabase();

    @Override
    public int update(String sql, Object... params) {
        int result;
        try {
            result = queryRunner.update(sql, params);
        } catch (SQLException e) {
            System.err.println("更新出错！");
            throw new RuntimeException(e);
        }
        System.out.println(sql);
        return result;
    }

    @Override
    public <T> List<T> queryList(String sql, Class<T> entityClass, Object... params){
        List<T> results = null;
        try {
            results = queryRunner.query(sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
