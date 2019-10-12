package com.quesa.mybootproject.common.page;

import com.alibaba.fastjson.JSONObject;
import com.quesa.mybootproject.common.util.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1440764264856L;
    /**
     * 当前页
     */
    public static final String g_pageNo = "pageNo";
    /**
     * 每页分页数
     */
    public static final String g_pageSize = "pageSize";

    Map<String, Object> extra;// 额外参数对象
    List<T> list;// 查询结果
    int pageNo = 1; // 当前页数
    int pageSize = 10; // 行数
    int maxSize = 0; // 总条数
    int maxPage = 0; // 总页数

    /**
     * 主键集合
     */
    public String ids;
    /**
     * 父主键集合
     */
    public String parentIds;

    /**
     * 是否需要分页查询 默认查询
     *
     * @time 2016年1月18日 00:34:02
     */
    int isPage = 1;

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("pageNo", pageNo);
            json.put("pageSize", pageSize);
            json.put("maxSize", maxSize);
            json.put("maxPage", maxPage);
            if (extra != null) {
                Iterator<String> s = extra.keySet().iterator();
                String key = "";
                while (s.hasNext()) {
                    key = s.next();
                    if (!StringUtil.isEmpty(key)) {
                        json.put(key, extra.get(key));
                    }
                    s.remove();
                }
                extra.clear();
                extra = null;
                key = null;
                s = null;
            }
        } catch (Exception e) {
        }
        return json;
    }

    /**
     * 获取 查询结果
     */
    public List<T> getList() {
        return list == null ? new ArrayList<T>(0) : list;
    }

    /**
     * 赋值 查询结果
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 获取 当前页
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 赋值 当前页
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取 行数
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 赋值 行数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取 总数
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * 赋值 总数
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 获取 页数
     */
    public int getMaxPage() {
        return maxPage;
    }

    /**
     * 赋值 页数
     */
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    /**
     * @return extra
     */
    public Map<String, Object> getExtra() {
        return extra;
    }

    /**
     * @param extra 要设置的 extra
     */
    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    /**
     * 是否需要分页查询 默认查询
     */
    public int getIsPage() {
        return isPage;
    }

    /**
     * 是否需要分页查询 默认查询
     */
    public void setIsPage(int isPage) {
        this.isPage = isPage;
    }

    public void clear() {
        if (this.extra == null) {
        } else {
            this.extra.clear();
            this.extra = null;
        }
        if (this.list == null) {
        } else {
            this.list.clear();
            this.list = null;
        }
    }
}
