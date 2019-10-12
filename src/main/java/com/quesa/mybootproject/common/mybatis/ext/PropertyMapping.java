package com.quesa.mybootproject.common.mybatis.ext;

/**
 * 属性映射信息
 *
 */
public class PropertyMapping {

    /**
     * 表字段名称
     */
    private String column;
    /**
     * 实体属性名称
     */
    private String property;

    /**
     * type
     */
    private Class<?> type;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

}
