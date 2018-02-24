package com.less.aspider.dao;

import java.util.List;

/**
 * Created by deeper on 2018/2/14.
 */

public interface DataAccessor {
    /**
     * 执行更新操作（包括：update、insert、delete），返回所更新的记录数
     */
    int update(String sql, Object... params);

    /**
     * 查询
     * @param sql
     * @param entityClass
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> queryList(String sql, Class<T> entityClass, Object... params);
}
