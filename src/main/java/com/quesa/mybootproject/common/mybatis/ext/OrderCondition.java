package com.quesa.mybootproject.common.mybatis.ext;

/**
 * 排序条件
 */
public class OrderCondition {

    /**
     * 排序列
     */
    private String orderColumn;

    /**
     * 排序方式
     */
    private String orderType;

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
