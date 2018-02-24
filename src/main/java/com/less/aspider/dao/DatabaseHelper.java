package com.less.aspider.dao;

import org.apache.commons.collections.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by deeper on 2018/2/14.
 * 参考: org.smart4j.framework.orm.DataSet;
 */

public abstract class DatabaseHelper {

    public abstract String dbname();
    /**
     * 获取数据访问器
     */
    private DataAccessor dataAccessor = new DefaultDataAccessor() {
        @Override
        protected String getDatabase() {
            return dbname();
        }
    };

    public void createTable(Class<?> clazz) {
        String sql = GenerateSQLHelper.generateCreateSql(clazz);
        int rows = dataAccessor.update(sql);
        System.out.println("result: " + rows);
    }

    /**
     * 插入一条数据
     * @param entity
     * @return
     */
    public boolean insert(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        Class<?> entityClass = entity.getClass();
        Map<String, Object> fieldMap = ObjectUtil.getFieldMap(entity);
        if (MapUtils.isEmpty(fieldMap)) {
            return true;
        }
        String sql = GenerateSQLHelper.generateInsertSql(entityClass, fieldMap.keySet());
        int rows = dataAccessor.update(sql, fieldMap.values().toArray());
        System.out.println("rows: " + rows);
        return rows > 0;
    }

    public <T> List<T> queryList(String sql, Class<T> clazz) {
        List<T> list = dataAccessor.queryList(sql, clazz, null);
        return list;
    }

    public <T> List<T> queryListByPage(String sql, int page, int pageSize, String orderby, Class<T> clazz) {
        int start = (page - 1) * pageSize;
        if (orderby == null || orderby.length() == 0) {
            sql = sql + " limit " + start + "," + pageSize;
        } else {
            sql = sql + " order by " + orderby + " limit " + start + "," + pageSize;
        }
        System.out.println(sql);
        List<T> list = dataAccessor.queryList(sql, clazz, null);
        return list;
    }
}
