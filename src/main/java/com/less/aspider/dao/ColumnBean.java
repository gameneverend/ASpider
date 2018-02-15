package com.less.aspider.dao;

/**
 * Created by deeper on 2018/2/15.
 */

public class ColumnBean {

    private String name;

    private String columnDefinition;

    public ColumnBean(String name, String columnDefinition) {
        this.name = name;
        this.columnDefinition = columnDefinition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnDefinition() {
        return columnDefinition;
    }

    public void setColumnDefinition(String columnDefinition) {
        this.columnDefinition = columnDefinition;
    }
}
