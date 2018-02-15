package com.less.aspider.dao;

import com.less.aspider.util.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

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
}
