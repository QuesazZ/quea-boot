package com.quesa.mybootproject.common.mybatis.ext;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体映射信息
 *
 */
public class EntityMapping {

    /**
     * 表名称
     */
    private String table;
    /**
     * 类名
     */
    private String className;
    /**
     * 属性映射列表
     */
    private List<PropertyMapping> list = new ArrayList<PropertyMapping>();

    /**
     * 排序列表
     */
    private List<OrderCondition> orderConditionList = new ArrayList<>();

    public EntityMapping(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<PropertyMapping> getPropertyMappings() {
        return list;
    }

    public void addPropertyMapping(PropertyMapping propertyMapping) {
        this.list.add(propertyMapping);
    }

    public List<OrderCondition> getOrderConditions() {
        return orderConditionList;
    }

    public void addOrderCondition(OrderCondition orderCondition) {
        this.orderConditionList.add(orderCondition);
    }
}
