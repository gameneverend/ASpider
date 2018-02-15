package com.less.aspider.dao;

import com.less.aspider.test.Person;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by deeper on 2018/2/14.
 */

public class GenerateSQLHelper {

    public static String generateCreateSql(Class<?> entityClass) {
        Map<String, ColumnBean> fieldsMap = getEntityFieldMap(entityClass);
        StringBuilder sql = new StringBuilder("create table if not exists ")
                .append(getTable(entityClass))
                .append("(");

        Collection<ColumnBean> collection = fieldsMap.values();
        int i = 0;
        for (ColumnBean columnBean : collection) {
            String column = columnBean.getName();
            String columnDefinition = columnBean.getColumnDefinition();
            if (i == collection.size() - 1) {
                sql.append(column + " " + columnDefinition);
            } else {
                sql.append(column + " " + columnDefinition + ",");
            }
            i++;
        }
        sql.append(")");
        return sql.toString();
    }

    public static String generateSelectSql(Class<?> entityClass) {
        StringBuilder sql = new StringBuilder("select * from ").append(getTable(entityClass));
        return sql.toString();
    }

    public static String generateInsertSql(Class<?> entityClass, Collection<String> fieldNames) {
        StringBuilder sql = new StringBuilder("insert into ").append(getTable(entityClass));
        int i = 0;
        StringBuilder columns = new StringBuilder(" ");
        StringBuilder values = new StringBuilder(" values ");
        for (String fieldName : fieldNames) {
            String columnName = getColumnName(entityClass, fieldName).getName();
            if (i == 0) {
                columns.append("(").append(columnName);
                values.append("(?");
            } else {
                columns.append(", ").append(columnName);
                values.append(", ?");
            }
            if (i == fieldNames.size() - 1) {
                columns.append(")");
                values.append(")");
            }
            i++;
        }
        sql.append(columns).append(values);
        return sql.toString();
    }

    private static String getTable(Class<?> entityClass) {
        String tableName = null;
        if (entityClass.isAnnotationPresent(Table.class)) {
            // 若已存在，则使用该注解中定义的表名
            String value = entityClass.getAnnotation(Table.class).value();
            if (value.isEmpty()) {
                tableName = entityClass.getSimpleName();
            } else {
                tableName = value;
            }
        }
        if (tableName == null) {
            throw new RuntimeException("can't find table annotation");
        }
        return tableName;
    }

    /**
     * 获取成员变量名和条件映射关系
     * @param entityClass
     * @return
     */
    private static Map<String, ColumnBean> getEntityFieldMap(Class<?> entityClass) {
        // 创建一个 fieldMap（用于存放列名与条件的映射关系）
        Map<String, ColumnBean> fieldMap = new HashMap<String, ColumnBean>();

        // 获取并遍历该实体类中所有的字段（不包括父类中的方法）
        Field[] fields = entityClass.getDeclaredFields();
        if (!ArrayUtils.isEmpty(fields)) {
            for (Field field : fields) {
                String fieldName = field.getName();
                String columnName;
                // 判断该字段上是否存在 Column 注解
                if (field.isAnnotationPresent(Column.class)) {
                    // 若已存在，则使用该注解中定义的列名
                    String name = field.getAnnotation(Column.class).value();
                    String columnDefinition = field.getAnnotation(Column.class).columnDefinition();
                    if (!name.isEmpty()) {
                        columnName = name;
                    } else {
                        columnName = fieldName.toLowerCase();
                    }
                    ColumnBean columnBean = new ColumnBean(columnName, columnDefinition);
                    fieldMap.put(fieldName, columnBean);
                }
            }
        }
        return fieldMap;
    }

    private static ColumnBean getColumnName(Class<?> entityClass, String fieldName) {
        Map<String, ColumnBean> fieldsMap = getEntityFieldMap(entityClass);
        return fieldsMap.get(fieldName);
    }

    public static void main(String[] args) {
        Person person = new Person("xiaoming","22");
        String sql = GenerateSQLHelper.generateCreateSql(person.getClass());
        System.out.println(sql);
    }
}
