package com.quesa.mybootproject.common.service;


import com.quesa.mybootproject.common.page.Page;
import com.quesa.mybootproject.common.persistence.BaseDao;
import com.quesa.mybootproject.common.persistence.DataEntity;
import com.quesa.mybootproject.common.util.DateUtil;
import com.quesa.mybootproject.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Service基类
 */
public abstract class BaseService<D extends BaseDao<T>, T extends DataEntity> {
    /**
     * 日志对象
     */
    protected Logger logger = Logger.getLogger(getClass());
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected void removeCacheById(String id) {
        if (StringUtil.isEmpty(id)) {
            return;
        }
        //清除缓存
//        try {
//            CacheManagerFactory.getInstance().deleteByKey(id);
//        } catch (Exception e) {
//        }
    }

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer insert(T entity) {
        Integer isOk = 0;
        entity.preInsert();
        isOk = dao.insert(entity);
        if (isOk > 0) {
            //保存到缓存
//            CacheManagerFactory.getInstance().set(entity.getId(), getById(entity.getId()),DateUtil.daySecond / 20);
        }
        return isOk;
    }

    @Transactional(readOnly = false)
    public Integer insertList(List<T> entityList) {
        Integer isOk = 0;
        for (T t : entityList) {
            t.preInsert();
        }
        isOk = dao.insertList(entityList);
        if (isOk > 0) {
            //保存到缓存
//            for (T t : entityList) {
//                CacheManagerFactory.getInstance().set(t.getId(), getById(t.getId()), DateUtil.daySecond / 20);
//            }
        }
        return isOk;
    }

    /**
     * <p>
     * 根据ID判断数据是否存在，如果存在则修改，反之新增。
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer save(T entity) {
        if (StringUtil.isEmpty(getById(entity.getId()))) {
            return insert(entity);
        } else {
            return update(entity);
        }
    }

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer deleteById(Serializable id) {
        Integer isOK = 0;
        isOK = dao.deleteById(id);
        //清除缓存
        if (isOK > 0) {
            removeCacheById((String) id);
        }
        return isOK;
    }

    /**
     * <p>
     * 根据 columnMap 条件，删除记录
     * </p>
     *
     * @param columnMap 表字段 map 对象
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer delete(Map<String, Object> columnMap) {
        return dao.delete(columnMap);
    }

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer deleteByIds(List<? extends Serializable> idList) {
        Integer isOK = 0;
        isOK = dao.deleteByIds(idList);
        //清除缓存
        if (isOK > 0) {
            for (Serializable id : idList) {
                removeCacheById((String) id);
            }
        }
        return isOK;
    }

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer update(T entity) {
        Integer isOK = 0;
        entity.preUpdate();
        isOK = dao.update(entity);
        //更新缓存
        if (isOK > 0) {
            removeCacheById(entity.getId());
        }
        return isOK;
    }

    /**
     * <p>
     * 根据 Ids批量修改
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer updateByIds(T entity, List<? extends Serializable> idList) {
        //更新缓存
        for (Serializable id : idList) {
            removeCacheById((String) id);
        }
        entity.preUpdate();
        return dao.updateByIds(entity, idList);
    }

    /**
     * <p>
     * 根据 whereEntity 条件，更新记录
     * </p>
     *
     * @param entity    实体对象
     * @param columnMap 实体对象封装操作类（可以为 null）
     * @return
     */
    @Transactional(readOnly = false)
    public Integer updateByMap(T entity, Map<String, Object> columnMap) {
        entity.preUpdate();
        return dao.updateByMap(entity, columnMap);
    }

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    public T getById(Serializable id) {
        return dao.getById(id);
    }

    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return List<T>
     */
    public List<T> getListByIds(List<? extends Serializable> idList) {
        return dao.getListByIds(idList);
    }

    /**
     * <p>
     * 查询（根据 columnMap 条件）
     * </p>
     *
     * @param entity 表字段 map 对象
     * @return List<T>
     */
    public List<T> getList(T entity) {
        return dao.getList(entity);
    }

    /**
     * <p>
     * 查询（根据 columnMap 条件）
     * </p>
     *
     * @param entity 表字段 map 对象
     * @return List<T>
     */
    public List<String> getIdList(T entity) {
        return dao.getIdList(entity);
    }


    /**
     * <p>
     * 查询（根据 columnMap 条件）查询一条记录
     * </p>
     *
     * @param entity 表字段 map 对象
     * @return List<T>
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * <p>
     * 根据 columnMap 条件，查询总记录数
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    public Integer getCount(T entity) {
        return dao.getCount(entity);
    }

    /**
     * <p>
     * 根据 entity 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param page
     * @return List<T>
     */
    public Page<T> getListPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.getList(entity));
        return page;
    }

    /**
     * <p>
     * 删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）
     * </p>
     *
     * @param entity
     * @return
     * @see public int delete(T entity)
     */
    @Transactional(readOnly = false)
    public Integer deleteLogicById(T entity) {
        Integer isOk = 0;
        T o = dao.getById(entity.getId());
        if (o != null) {
            entity.preDeleteLogic();
            isOk = dao.update(entity);
        }
        if (isOk > 0) {
            //清除缓存
            removeCacheById(entity.getId());
        }
        return isOk;
    }

    /**
     * <p>
     * 删除数据（逻辑删除，更新is_delete_字段为1,在表包含字段is_delete_时，可以调用此方法，将数据隐藏）
     * </p>
     *
     * @param idList 主键ID列表
     * @return int
     */
    @Transactional(readOnly = false)
    public Integer deleteLogicByIds(T entity, List<? extends Serializable> idList) {
        //清除缓存
        removeCacheById(entity.getId());
        entity.preDeleteLogic();
        return dao.updateByIds(entity, idList);
    }

}
