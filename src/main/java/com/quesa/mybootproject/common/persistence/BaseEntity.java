package com.quesa.mybootproject.common.persistence;

import com.quesa.mybootproject.common.page.Page;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Entity支持类
 *
 * @author jeeplus
 * @version 2014-05-16
 */
public abstract class BaseEntity<T extends DataEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    public BaseEntity() {
    }

    /**
     * 排序字段,此处为数据库sql字段,多字段排序请用【，】分割
     */
    protected transient String sortFields;
    /**
     * 排序类型，升序，降序，默认降序
     */
    protected transient String sortType;

    /**
     * 关键字检索名称
     */
    protected transient String likeName;

    /**
     * 非实体类参数传递查询
     */
    protected transient Map<String, Object> ov;

    protected transient Page<T> page;

    public Map<String, Object> getOv() {
        if (ov == null) {
            ov = new HashMap<>();
        }
        return ov;
    }

    public void setOv(Map<String, Object> ov) {
        this.ov = ov;
    }

    public Page<T> getPage() {
        if (page == null) {
            page = new Page<T>();
        }
        return page;
    }

    public void setPage(Page<T> page) {

        this.page = page;
    }

    public String getSortFields() {
        // SQL过滤，防止注入
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sortFields == null || sqlPattern.matcher(sortFields).find()) {
            return null;
        }
        return sortFields;
    }

    public void setSortFields(String sortFields) {
        this.sortFields = sortFields;
    }

    public String getSortType() {
        if (sortType == null || "".equals(sortType)) {
            sortType = SORT_DESC;//默认降序
        }
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }

    public String getLikeName() {
        return likeName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * 排序方式
     */
    public static final String SORT_DESC = "DESC";
    public static final String SORT_ASC = "ASC";

}
