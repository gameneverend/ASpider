package com.less.aspider.dao;

import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * Created by deeper on 2018/2/14.
 * 参考: org.smart4j.framework.orm.DataSet;
 */

public class DatabaseHelper {

    /**
     * 获取数据访问器
     */
    private static final DataAccessor dataAccessor = new DefaultDataAccessor() {
        @Override
        protected String getDatabase() {
            return "test";
        }
    };

    /**
     * 插入一条数据
     * @param entity
     * @return
     */
    public static boolean insert(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        Class<?> entityClass = entity.getClass();
        Map<String, Object> fieldMap = ObjectUtil.getFieldMap(entity);
        return insert(entityClass, fieldMap);
    }

    /**
     * 插入一条数据
     */
    private static boolean insert(Class<?> entityClass, Map<String, Object> fieldMap) {
        if (MapUtils.isEmpty(fieldMap)) {
            return true;
        }
        String sql = GenerateSQLHelper.generateInsertSql(entityClass, fieldMap.keySet());
        int rows = dataAccessor.update(sql, fieldMap.values().toArray());
        return rows > 0;
    }
}
