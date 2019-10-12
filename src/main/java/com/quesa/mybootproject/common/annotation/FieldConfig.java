package com.quesa.mybootproject.common.annotation;


import com.quesa.mybootproject.common.util.StringUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段配置
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldConfig {
    /**
     * 显示名称
     */
    String showName() default StringUtil.EMPTY_STRING;

    /**
     * 字典Mark值
     */
    String dictMark() default StringUtil.EMPTY_STRING;

    /**
     * 字段值解释
     * <p>
     * 格式：
     * 1@是,2@否......
     * 第一位为实际值即数据值，第二位为分隔符，第三位为显示值
     */
    String valExplain() default StringUtil.EMPTY_STRING;

    /**
     * 数据源标识
     */
    String dataSourceKey() default StringUtil.EMPTY_STRING;

    /**
     * 日期格式
     */
    String dateFormat() default StringUtil.EMPTY_STRING;

    /**
     * 是否忽略
     */
    boolean isIgnored() default false;

    /**
     * 排序列
     */
    String orderColumn() default StringUtil.EMPTY_STRING;

    /**
     * 排序方式
     */
    String orderType() default "asc";
}
