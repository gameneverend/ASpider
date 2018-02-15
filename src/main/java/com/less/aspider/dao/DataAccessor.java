package com.less.aspider.dao;

/**
 * Created by deeper on 2018/2/14.
 */

public interface DataAccessor {
    /**
     * 执行更新操作（包括：update、insert、delete），返回所更新的记录数
     */
    int update(String sql, Object... params);
}
