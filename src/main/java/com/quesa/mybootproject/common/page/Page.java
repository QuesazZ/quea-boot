package com.quesa.mybootproject.common.page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quesa.mybootproject.common.persistence.DataEntity;
import com.quesa.mybootproject.common.util.StringUtil;

import java.util.List;

public class Page<T extends DataEntity> {

    public static final int DEFAULT_PAGE_SIZE = -1;

    protected int pageNo = 1; // 当前页, 默认为第1页
    protected int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数,设置为“-1”表示不进行分页（分页无效）
    protected int totalRecord = -1; // 总记录数, 默认为-1, 表示需要查询
    protected int totalPage = -1; // 总页数

    protected List<T> list; // 当前页记录List形式

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        computeTotalPage();
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        computeTotalPage();
    }

    protected void computeTotalPage() {
        if (getPageSize() > 0) {
            this.totalPage = (int) (getTotalRecord() % getPageSize() == 0 ? getTotalRecord() / getPageSize() : getTotalRecord() / getPageSize() + 1);
        } else {
            this.totalPage = -1;
        }
    }

    /**
     * 获取 Hibernate FirstResult
     */
    public int getFirstResult() {
        int firstResult = (getPageNo() - 1) * getPageSize();
        if (firstResult >= getTotalRecord() || firstResult < 0) {
            firstResult = 0;
        }
        return firstResult;
    }

    /**
     * 获取 Hibernate MaxResults
     */
    public int getMaxResults() {
        return getPageSize();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> results) {
        this.list = results;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize)
                .append(", totalRecord=").append(totalRecord < 0 ? "null" : totalRecord).append(", totalPage=")
                .append(totalPage < 0 ? "null" : totalPage).append(", curPageObjects=").append(list == null ? "null" : list.size()).append("]");
        return builder.toString();
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("pageNo", pageNo);
            json.put("pageSize", pageSize);
            json.put("totalRecord", totalRecord);
            json.put("totalPage", totalPage);
            JSONArray array = new JSONArray();
            if (getList() != null && getList().size() > 0) {
                for (T o : getList()) {
                    try {
                        array.add(o.toJSONObjcet());//错误的信息不导出来，不能因为有发生异常，导致全部无数据
                    }
                    catch (Exception e)
                    {
                        StringUtil.getStackMsg(e);
                        continue;
                    }
                }
            }
            json.put("list", array);
        } catch (Exception e) {
            StringUtil.getStackMsg(e);
            json.put("list", new JSONArray());
        }
        return json;
    }

    public JSONObject toSimpleJSONObjcet() {
        JSONObject json = new JSONObject();
        try {
            json.put("pageNo", pageNo);
            json.put("pageSize", pageSize);
            json.put("totalRecord", totalRecord);
            json.put("totalPage", totalPage);
            JSONArray array = new JSONArray();
            if (getList() != null && getList().size() > 0) {
                for (T o : getList()) {
                    try
                    {
                        array.add(o.toSimpleJSONObjcet());
                    }
                    catch (Exception e)
                    {
                        StringUtil.getStackMsg(e);
                        continue;
                    }
                }
            }
            json.put("list", array);
        } catch (Exception e) {
            StringUtil.getStackMsg(e);
            json.put("list", new JSONArray());
        }
        return json;
    }

    /**
     * 绑定分页参数
     *
     * @param jo
     */
    public void bindPageParams(JSONObject jo) {
        jo.put("pageNo", pageNo);
        jo.put("pageSize", pageSize);
        jo.put("totalRecord", totalRecord);
        jo.put("totalPage", totalPage);
    }

}