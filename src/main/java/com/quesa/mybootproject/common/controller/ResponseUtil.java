package com.quesa.mybootproject.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.quesa.mybootproject.common.util.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.StringTokenizer;


public class ResponseUtil {

    /**
     * 创建日志实例
     */
    private static final Logger logger = LogManager.getLogger(BaseController.class.getName());

    /**
     * 获取当前请求返回对象
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前请求对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 输出信息到页面
     *
     * @param jsonStr 格式字符串
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(String jsonStr) {
        writeJSON(jsonStr, Constants.SYSTEM_SUCCESS);
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param str 返回文本字符串
     * @throws IOException
     * @throws JSONException
     */
    public static void writeString(String str) {
        try {
            sendToWeb(str);
        } catch (Exception e) {
            logger.error(e);
        }
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param jsonStr 格式字符串
     * @param codeMap 用map记录状态码和消息
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(String jsonStr, Map<String, String> codeMap) {
        JSONObject jsonObject = new JSONObject(2);
        JSONObject statusObject = new JSONObject(2);
        try {
            jsonObject.put(Constants.RESULT, jsonStr);
            statusObject.put(Constants.CODE, codeMap.get(Constants.CODE));
            statusObject.put(Constants.MSG, codeMap.get("msg"));
            jsonObject.put(Constants.STATUS, statusObject);
            sendToWeb(jsonObject);
        } catch (Exception e) {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param jsonObj 格式对象
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(JSONObject jsonObj) {
        writeJSON(jsonObj, Constants.SYSTEM_SUCCESS);
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param codeMap 用map记录状态码和消息
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(Map<String, String> codeMap) {
        JSONObject jsonObject = new JSONObject(2);
        JSONObject statusObject = new JSONObject(2);
        try {
            jsonObject.put(Constants.RESULT, "");
            statusObject.put(Constants.CODE, codeMap.get(Constants.CODE));
            statusObject.put(Constants.MSG, codeMap.get(Constants.MSG));
            jsonObject.put(Constants.STATUS, statusObject);
            sendToWeb(jsonObject);
            statusObject.clear();
            codeMap = null;
        } catch (Exception e) {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param jsonStr 格式字符串
     * @param code    状态码
     * @param msg     消息
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(String jsonStr, String code, String msg) {
        JSONObject jsonObject = new JSONObject(2);
        JSONObject statusObject = new JSONObject(2);
        try {
            jsonObject.put(Constants.RESULT, jsonStr);
            statusObject.put(Constants.CODE, code);
            statusObject.put(Constants.MSG, msg);
            jsonObject.put(Constants.STATUS, statusObject);
            sendToWeb(jsonObject);
            statusObject.clear();
            jsonStr = null;
            code = null;
            msg = null;
        } catch (Exception e) {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param jsonObj 输出对象
     * @param codeMap 输出状态
     */
    public static void writeJSON(JSONObject jsonObj, Map<String, String> codeMap) {

        JSONObject jsonObject = new JSONObject(2);
        JSONObject statusObject = new JSONObject(2);
        try {
            jsonObject.put(Constants.RESULT, jsonObj);
            statusObject.put(Constants.CODE, new String(codeMap.get(Constants.CODE)));
            statusObject.put(Constants.MSG, new String(codeMap.get(Constants.MSG)));
            jsonObject.put(Constants.STATUS, statusObject);
            sendToWeb(jsonObject);
            statusObject.clear();
            jsonObj.clear();
            jsonObj = null;
            statusObject.clear();
            statusObject = null;
            jsonObject.clear();
            jsonObject = null;
            codeMap = null;
        } catch (Exception e) {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param jsonObj 格式对象
     * @param code    状态码
     * @param msg     消息
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(JSONObject jsonObj, String code, String msg) {

        JSONObject jsonObject = new JSONObject(2);
        JSONObject statusObject = new JSONObject(2);
        try {
            jsonObject.put(Constants.RESULT, jsonObj);
            statusObject.put(Constants.CODE, code);
            statusObject.put(Constants.MSG, msg);
            jsonObject.put(Constants.STATUS, statusObject);
            sendToWeb(jsonObject);
            statusObject.clear();
            code = null;
            msg = null;
        } catch (Exception e) {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param code 状态码
     * @param msg  消息
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(String code, String msg) {
        JSONObject jsonObject = new JSONObject(2);
        JSONObject statusObject = new JSONObject(2);
        try {
            jsonObject.put(Constants.RESULT, "");
            statusObject.put(Constants.CODE, code);
            statusObject.put(Constants.MSG, msg);
            jsonObject.put(Constants.STATUS, statusObject);
            sendToWeb(jsonObject);
            statusObject.clear();
            code = null;
            msg = null;
        } catch (Exception e) {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    /**
     * 输出信息到页面
     *
     * @param object 输出对象
     * @throws IOException
     * @throws JSONException
     */
    public static void writeJSON(Object object) {
        String str = "";
        try {
            if (object != null) {
                str = JSON.toJSONString(object);
            }
            sendToWeb(str);
            str = null;
            object = null;
        } catch (Exception e) {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    public static void writeJSON(JSONArray object, Map<String, String> codeMap) {
        try {
            String str = "";
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("list", object);
            JSONObject jsonObject = new JSONObject(2);
            JSONObject statusObject = new JSONObject(2);
            jsonObject.put(Constants.RESULT, jsonObj);
            statusObject.put(Constants.CODE, new String(codeMap.get(Constants.CODE)));
            statusObject.put(Constants.MSG, new String(codeMap.get(Constants.MSG)));
            jsonObject.put(Constants.STATUS, statusObject);
            sendToWeb(jsonObject);
            statusObject.clear();
            jsonObj.clear();
            statusObject.clear();
            jsonObject.clear();
        } catch (
                Exception e)

        {
            logger.error("ControllerSupper中writeJSON异常", e);
        }
        return;
    }

    /**
     * 将json返回到前端
     *
     * @param jsonObject
     * @throws IOException
     */
    private static void sendToWeb(JSONObject jsonObject) throws IOException {
        if (getRequest() == null || getResponse() == null) {
            return;
        }
        boolean jsonP = false;
        Object o = getRequest().getParameter("callback");
        String cb = (o == null ? null : o.toString());
        if (cb == null || "".equals(cb)) {

        } else {
            jsonP = true;
        }
        getResponse().setContentType("text/javascript;charset=UTF-8");
        getResponse().addHeader("Set-Cookie","HttpOnly");
        // getResponse().setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = new PrintWriter(getResponse().getWriter(), true);
            if (jsonP) {
                out.print(cb + "(");
            }
            if ("1".equals(getRequest().getHeader("isEncrypt"))) {
                try {
                    String result = jsonObject.toJSONString();
                    String token = getRequest().getHeader(Constants.TOKEN);
                    String timeStamp = getRequest().getHeader("timeStamp");
                    getResponse().addHeader(Constants.TOKEN, token);
                    getResponse().addHeader("timeStamp", timeStamp);
                    setEtag(timeStamp);
                    result = Base64Encrypt.encodeSalt(result, token, timeStamp);
//                    JSONObject.writeJSONStringTo(result, out, SerializerFeature.BeanToArray);
                    JSONObject.writeJSONString(out,result,SerializerFeature.BeanToArray);
                } catch (Exception e) {

                }
            } else {
//                JSONObject.writeJSONStringTo(jsonObject, out, SerializerFeature.BeanToArray);
                JSONObject.writeJSONString(out,jsonObject,SerializerFeature.BeanToArray);
            }

            if (jsonP) {
                out.print(");");
            }
            jsonObject.clear();
        } finally {
            if (null != out) {
                try {
                    out.flush();
                    out.close();
                    out = null;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将json返回到前端
     *
     * @param jsonObject
     * @throws IOException
     */
    public static void sendToWeb(String jsonObject) throws IOException {
        if (getRequest() == null || getResponse() == null) {
            return;
        }
        boolean jsonP = false;
        Object o = getRequest().getParameter("callback");
        String cb = (o == null ? null : o.toString());
        if (cb == null || "".equals(cb)) {
        } else {
            jsonP = true;
        }
        getResponse().setContentType("text/javascript;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = new PrintWriter(getResponse().getWriter(), true);
            if (jsonP) {
                out.print(cb + "(");
            }
            String result = jsonObject;
            if ("1".equals(getRequest().getHeader("isEncrypt"))) {
                try {
                    String token = getRequest().getHeader(Constants.TOKEN);
                    String timeStamp = getRequest().getHeader("timeStamp");
                    getResponse().addHeader(Constants.TOKEN, token);
                    getResponse().addHeader("timeStamp", timeStamp);
                    setEtag(timeStamp);
                    result = Base64Encrypt.encodeSalt(result, token, timeStamp);
                } catch (Exception e) {

                }

            }
            out.print(result);
            if (jsonP) {
                out.print(");");
            }
            out.flush();
            jsonObject = null;
        } finally {
            if (null != out) {
                try {
                    out.close();
                    out = null;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置客户端缓存过期时间 的Header.
     */
    public static void setExpiresHeader(long expiresSeconds) {
        // Http 1.0 header, set a fix expires date.
        getResponse().setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + expiresSeconds * 1000);
        // Http 1.1 header, set a time after now.
        getResponse().setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     */
    public static void setNoCacheHeader() {
        // Http 1.0 header
        getResponse().setDateHeader(HttpHeaders.EXPIRES, 1L);
        getResponse().addHeader(HttpHeaders.PRAGMA, "no-cache");
        // Http 1.1 header
        getResponse().setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
    }

    /**
     * 设置LastModified Header.
     */
    public static void setLastModifiedHeader(long lastModifiedDate) {
        getResponse().setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
    }

    /**
     * 设置Etag Header.
     */
    public static void setEtag(String etag) {
        getResponse().setHeader(HttpHeaders.ETAG, etag);
    }

    /**
     * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
     * <p>
     * 如果无修改, checkIfModify返回false ,设置304 not modify status.
     *
     * @param lastModified 内容的最后修改时间.
     */
    public static boolean checkIfModifiedSince(long lastModified) {
        long ifModifiedSince = getRequest().getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
        if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
            getResponse().setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return false;
        }
        return true;
    }

    /**
     * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
     * <p>
     * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
     *
     * @param etag 内容的ETag.
     */
    public static boolean checkIfNoneMatchEtag(String etag) {
        String headerValue = getRequest().getHeader(HttpHeaders.IF_NONE_MATCH);
        if (headerValue != null) {
            boolean conditionSatisfied = false;
            if (!"*".equals(headerValue)) {
                StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

                while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(etag)) {
                        conditionSatisfied = true;
                    }
                }
            } else {
                conditionSatisfied = true;
            }

            if (conditionSatisfied) {
                getResponse().setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                getResponse().setHeader(HttpHeaders.ETAG, etag);
                return false;
            }
        }
        return true;
    }

    /**
     * 设置让浏览器弹出下载对话框的Header.
     *
     * @param fileName 下载后的文件名.
     */
    public static void setFileDownloadHeader(String fileName) {
        try {
            // 中文文件名支持
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            getResponse().setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        }
    }


    /**
     * 转换json结果字符串
     *
     * @param jsonstr 结果字符串
     */
    public static JSONObject convertMicroServiceList(String jsonstr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        JSONObject returnJsonObject = new JSONObject();
        returnJsonObject.put("list",jsonObject.getJSONArray("records"));
        returnJsonObject.put("pageNo",jsonObject.getIntValue("current"));
        returnJsonObject.put("pageSize",jsonObject.getIntValue("size"));
        returnJsonObject.put("totalPage",jsonObject.getIntValue("pages"));
        returnJsonObject.put("totalRecord",jsonObject.getIntValue("total"));
        return returnJsonObject;
    }

}
