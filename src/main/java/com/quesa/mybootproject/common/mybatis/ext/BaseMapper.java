package com.quesa.mybootproject.common.mybatis.ext;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {

    public Integer insert(T entity);

    public Integer insertList(@Param("entityList") List<T> entityList);

    public Integer update(T entity);

    public Integer updateByMap(@Param("entity") T entity, @Param("condition") T condition);

    public T getById(Serializable id);

    public T get(T entity);

    public List<T> getList(T entity);

    public List<T> getListByIds(@Param("idList") List<? extends Serializable> idList);

    public List<String> getIdList(T entity);

    public Integer getCount(T entity);

    public Integer deleteById(Serializable id);

    public Integer delete(T entity);

    public T getBySql(@Param("sql") String sql, @Param("params") Object params);

    public Integer executeInsertBySql(@Param("sql") String sql, @Param("params") Object params);

    public Integer executeUpdateBySql(@Param("sql") String sql, @Param("params") Object params);

    public Integer executeDeleteBySql(@Param("sql") String sql, @Param("params") Object params);

    public T executeQueryOneBySql(@Param("sql") String sql, @Param("params") Object params);

    public List<T> executeQueryListBySql(@Param("sql") String sql, @Param("params") Object params);


}
