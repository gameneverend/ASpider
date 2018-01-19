package com.less.aspider.dao;

import com.less.aspider.db.BaseDao;
import com.less.aspider.db.DBHelper;
import com.less.aspider.db.Result;

import java.util.List;

/**
 * Created by deeper on 2018/1/19.
 */

public abstract class SimpleDao<T> implements BaseDao<T> {

    protected String tableName = getEntityClass().getSimpleName();

    @Override
    public void createTable() {
        String sql = generateTableSQL();
        DBHelper.execSQL(sql);
    }

    @Override
    public abstract void save(T entity);

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void saveOrUpdate(T entity) {

    }

    @Override
    public T getById(Long id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public Result<T> getPage(int currentPage) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<T> search(String text) {
        return null;
    }

    protected abstract String generateTableSQL();

    @Override
    public abstract Class getEntityClass();
}
