package com.quesa.mybootproject.common.persistence;

import com.quesa.mybootproject.common.mybatis.ext.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DAO支持类实现
 */
public interface BaseDao<T extends DataEntity> extends BaseMapper<T> {
    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Override
    Integer insert(T entity);

    /**
     * <p>
     * 批量插入
     * </p>
     *
     * @param entityList 实体对象集合
     * @return int
     */
    @Override
    Integer insertList(@Param("entityList") List<T> entityList);

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return int
     */
    @Override
    Integer deleteById(Serializable id);

    /**
     * <p>
     * 根据 columnMap 条件删除
     * </p>
     *
     * @param columnMap 表字段 map 对象
     * @return int
     */
    Integer delete(Map<String, Object> columnMap);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     * @return int
     */
    Integer deleteByIds(@Param("idList") List<? extends Serializable> idList);

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Override
    Integer update(T entity);

    /**
     * <p>
     * 根据 Ids批量修改
     * </p>
     *
     * @param idList
     * @return int
     */
    Integer updateByIds(@Param("et") T entity, @Param("idList") List<? extends Serializable> idList);

    /**
     * <p>
     * 根据 columnMap 条件，更新记录
     * </p>
     *
     * @param entity    实体对象
     * @param columnMap 实体对象封装操作类（可以为 null）
     * @return
     */
    Integer updateByMap(@Param("et") T entity, @Param("cm") Map<String, Object> columnMap);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    @Override
    T getById(Serializable id);

    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return List<T>
     */
    @Override
    List<T> getListByIds(@Param("idList") List<? extends Serializable> idList);

    /**`
     * <p>
     * 查询（根据 columnMap 条件）
     * </p>
     *
     * @param entity 表字段 map 对象
     * @return List<T>
     */
    @Override
    List<T> getList(T entity);

    /**
     * <p>
     * 查询（根据 columnMap 条件）
     * </p>
     *
     * @param entity 表字段 map 对象
     * @return List<T>
     */
    @Override
    List<String> getIdList(T entity);

    /**
     * <p>
     * 查询（根据 columnMap 条件）查询一条记录
     * </p>
     *
     * @param entity 表字段 map 对象
     * @return List<T>
     */
    @Override
    T get(T entity);

    /**
     * <p>
     * 根据 columnMap 条件，查询总记录数
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Override
    Integer getCount(T entity);

    /**
     * <p>
     * 删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）
     * </p>
     *
     * @param id
     * @return
     * @see public int delete(T entity)
     */
    Integer deleteLogicById(Serializable id);

    /**
     * <p>
     * 删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）
     * </p>
     *
     * @param idList 主键ID列表
     * @return int
     */
    Integer deleteLogicByIds(T entity, List<? extends Serializable> idList);

    /**
     * <p>
     * 删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）
     * </p>
     *
     * @param columnMap
     * @return
     */
    Integer deleteLogic(Map<String, Object> columnMap);
}