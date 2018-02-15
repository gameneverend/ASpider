package com.less.aspider.dao;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by deeper on 2018/2/14.
 */

public class ObjectUtil {

    /**
     * 设置成员变量
     */
    public static void setField(Object obj, String fieldName, Object fieldValue) {
        try {
            if (PropertyUtils.isWriteable(obj, fieldName)) {
                PropertyUtils.setProperty(obj, fieldName, fieldValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取成员变量
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object propertyValue = null;
        try {
            if (PropertyUtils.isReadable(obj, fieldName)) {
                propertyValue = PropertyUtils.getProperty(obj, fieldName);
            }
        } catch (Exception e) {
            System.err.println("获取成员变量出错！");
            throw new RuntimeException(e);
        }
        return propertyValue;
    }

    /**
     * 获取对象的字段键值对（字段名 => 字段值），忽略 static 字段
     */
    public static Map<String, Object> getFieldMap(Object obj) {
        Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String fieldName = field.getName();
            Object fieldValue = ObjectUtil.getFieldValue(obj, fieldName);
            fieldMap.put(fieldName, fieldValue);
        }
        return fieldMap;
    }
}
