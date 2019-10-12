package com.quesa.mybootproject.common.controller;

import com.alibaba.fastjson.JSONObject;

import com.quesa.mybootproject.common.exception.ParamException;
import com.quesa.mybootproject.common.util.Constants;
import com.quesa.mybootproject.common.util.DateUtil;
import com.quesa.mybootproject.common.util.Encodes;
import com.quesa.mybootproject.common.util.StringUtil;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 */
public class RequestUtil {
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
     * 创建日志实例
     */
    private static final Logger logger = LogManager.getLogger(BaseController.class.getName());

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 参数
     * @return String
     */
    public static String getParameterToString(String parameter) {
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        return obj == null ? null : obj.toString().trim();
    }

    /**
     * 获取用户传入参数
     *
     * @param parameter    参数
     * @param defaultValue 默认值
     * @return
     */
    public static String getParameterToString(String parameter, Object defaultValue) {
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        return obj == null || "".equals(obj) ? (String) defaultValue : obj.toString().trim();
    }

    /**
     * 获取用户传入的参数信息
     *
     * @param parameter 参数
     * @param msg       自定义消息
     * @return String
     */
    public static String getParameterToString(String parameter, String msg) {
        Object obj = null;
        Map codeMap = Constants.setMsg(msg);
        obj = rederParmeter(getRequest(), parameter, codeMap);
        // 如果有提示消息，则参数不能为空
        if (StringUtil.isEmpty(obj)) {
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        codeMap = null;
        return obj == null ? null : obj.toString().trim();
    }
    /**
     *
     * 获取请求中的查询参数（如果是数组转换成为Map）
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        String data = (String) request.getAttribute("requestParams");
        Map<String,Object> parameterMap=null;
        JSONObject json=null;
        if (data == null || "".equals(data) || data.indexOf("<xml>") == 0) {
            return null;
        } else {
            // data参数封装
           json = JSONObject.parseObject(data);
           parameterMap=json;
        }
        for (String key : parameterMap.keySet()) {
            if (json.containsKey(key)) {// 在data中查找对应key
                map.put(key, json.get(key) == null ? "" : json.get(key).toString());
                if (json.containsKey(Constants.PARAMS)) {// 在param中查找对应key
                    JSONObject jsons = JSONObject.parseObject(json.get(Constants.PARAMS).toString());
                    Map<String,Object>   parameterMaps=jsons;
                    for (String key1 : parameterMaps.keySet()) {
                        if (jsons.containsKey(key1)) {
                            map.put(key1, jsons.get(key1) == null ? "" : jsons.get(key1).toString());
                        }
                    }

                }
            }
        }
        return map;
    }

    /**
     * 获取map集合
     *
     * @param
     * @param
     * @return
     */
    public static Map<String, String> getParamers() {
        HttpServletRequest request = getRequest();
        Map<String, String> map = new HashMap<String, String>();
        String data = (String) request.getAttribute("requestParams");
        Map<String,Object> parameterMap=null;
        JSONObject json=null;
        if (data == null || "".equals(data) || data.indexOf("<xml>") == 0) {
            return null;
        } else {
            // data参数封装
            json = JSONObject.parseObject(data);
            parameterMap=json;
        }
        for(String key : parameterMap.keySet()){
            map.put(key,parameterMap.get(key).toString());
        }
      return map;
    }
    /*
     * 获取用户传入的参数信息
     *
     * @param parameter 参数
     * @param codeMap   自定义消息
     * @return String
     */
    public static String getParameterToString(String parameter, Map codeMap) {
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter, codeMap);
        // 如果有提示消息，则参数不能为空
        if (StringUtil.isEmpty(obj)) {
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        codeMap = null;
        return obj == null ? null : obj.toString().trim();
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 参数
     * @return Integer
     */
    public static Integer getParameterToInteger(String parameter) {
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        // 转换成指定类型
        Integer value = null;
        if (obj != null) {
            try {
                if (!StringUtil.isEmpty(obj.toString().trim())) {
                    value = Integer.parseInt(obj.toString().trim());
                }
            } catch (Exception e) {
                logger.error("RequestUtil中getParameterByInt参数转换异常", e);
                throw new ParamException(Constants.SYSTEM_CONVERT_INT.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constants.SYSTEM_CONVERT_INT.get(Constants.MSG));
            }
        }
        obj = null;
        return value;
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter    参数
     * @param defaultValue 如果为空，则显示默认值
     * @return Integer
     */
    public static Integer getParameterToInteger(String parameter, Object defaultValue) {
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        // 转换成指定类型
        Integer value = null;
        if (obj != null) {
            try {
                if (!StringUtil.isEmpty(obj.toString().trim())) {
                    value = Integer.parseInt(obj.toString().trim());
                }
            } catch (Exception e) {
                logger.error("RequestUtil中getParameterByInt参数转换异常", e);
                throw new ParamException(Constants.SYSTEM_CONVERT_INT.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constants.SYSTEM_CONVERT_INT.get(Constants.MSG));
            }
        }
        obj = null;
        return value == null ? (Integer) defaultValue : value;
    }

    /**
     * 获取用户传入的参数信息
     *
     * @param parameter 参数
     * @param msg       自定义消息
     * @return Integer
     */
    public static Integer getParameterToInteger(String parameter, String msg) {
        // 转换成指定类型
        Integer value = null;
        Object obj = null;
        Map<String, String> codeMap = Constants.setMsg(msg);
        obj = rederParmeter(getRequest(), parameter, codeMap);
        // 开启验证参数是否为空
        if (StringUtil.isEmpty(obj)) {
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        try {
            value = Integer.parseInt(obj.toString().trim());
        } catch (Exception e) {
            logger.error("RequestUtil中getParameterByInt参数转换异常", e);
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        obj = null;
        codeMap = null;
        return value;
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 参数
     * @return Long
     */
    public static Long getParameterToLong(String parameter) {
        // 转换成指定类型
        Long value = null;
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        if (obj != null) {
            try {
                if (!StringUtil.isEmpty(obj.toString().trim())) {
                    value = Long.parseLong(obj.toString().trim());
                }
            } catch (Exception e) {
                logger.error("RequestUtil中getParameterByLong参数转换异常", e);
                Map<String, String> codeMap = Constants.SYSTEM_CONVERT_LONG;
                // 终止程序运行
                throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
            }
        }
        obj = null;
        return value;
    }

    /**
     * 获取用户传入的参数信息
     *
     * @param parameter 参数
     * @param msg       自定义消息
     * @return Long
     */
    public static Long getParameterToLong(String parameter, String msg) {
        // 转换成指定类型
        Long value = null;
        Object obj = null;
        Map<String, String> codeMap = Constants.setMsg(msg);
        obj = rederParmeter(getRequest(), parameter, codeMap);
        // 开启验证参数是否为空
        if (StringUtil.isEmpty(obj)) {
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        try {
            value = Long.parseLong(obj.toString().trim());
        } catch (Exception e) {
            logger.error("RequestUtil中getParameterByLong参数转换异常", e);
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        obj = null;
        codeMap = null;
        return value;
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 参数
     * @param point     保留小数点
     * @return Float
     */
    public static float getParameterToFloat(String parameter, int point) {
        // 转换成指定类型
        Float value = null;
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        if (obj != null) {
            try {
                if (!StringUtil.isEmpty(obj.toString().trim())) {
                    value = new BigDecimal(obj.toString().trim()).setScale(point, BigDecimal.ROUND_HALF_UP).floatValue();
                }

            } catch (Exception e) {
                logger.error("RequestUtil中getParameterByFloat参数转换异常", e);
                // 终止程序运行
                throw new ParamException(Constants.SYSTEM_CONVERT_FLOAT.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constants.SYSTEM_CONVERT_FLOAT.get(Constants.MSG));
            }
        }
        obj = null;
        return value;
    }

    /**
     * 获取用户传入的参数信息
     *
     * @param parameter 参数
     * @param point     保留小数点
     * @param msg       自定义消息
     * @return Float
     */
    public static Float getParameterToFloat(String parameter, int point, String msg) {
        // 转换成指定类型
        Float value = null;
        Object obj = null;
        Map<String, String> codeMap = Constants.setMsg(msg);
        obj = rederParmeter(getRequest(), parameter, codeMap);
        // 开启验证参数是否为空
        if (StringUtil.isEmpty(obj)) {
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }

        try {
            value = new BigDecimal(obj.toString().trim()).setScale(point, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (Exception e) {
            logger.error("RequestUtil中getParameterByFloat参数转换异常", e);
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        obj = null;
        codeMap = null;
        return value;
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 参数
     * @param point     保留小数点
     * @return Double
     */
    public static Double getParameterToDouble(String parameter, int point) {
        // 转换成指定类型
        Double value = null;
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        if (obj != null) {
            try {
                if (!StringUtil.isEmpty(obj.toString().trim())) {
                    value = new BigDecimal(obj.toString().trim()).setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
                }

            } catch (Exception e) {
                logger.error("RequestUtil中getParameterByFloat参数转换异常", e);
                // 终止程序运行
                throw new ParamException(Constants.SYSTEM_CONVERT_DOUBLE.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constants.SYSTEM_CONVERT_DOUBLE.get(Constants.MSG));
            }
        }
        obj = null;
        return value;
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 参数
     * @param point     保留小数点
     * @return Double
     */
    public static Double getParameterToDouble(String parameter, int point, Double defaultValue) {
        // 转换成指定类型
        Double value = defaultValue;
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        if (obj != null) {
            try {
                if (!StringUtil.isEmpty(obj.toString().trim())) {
                    value = new BigDecimal(obj.toString().trim()).setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
                }

            } catch (Exception e) {
                logger.error("RequestUtil中getParameterByFloat参数转换异常", e);
                // 终止程序运行
                throw new ParamException(Constants.SYSTEM_CONVERT_DOUBLE.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constants.SYSTEM_CONVERT_DOUBLE.get(Constants.MSG));
            }
        }
        obj = null;
        return value;
    }

    /**
     * 获取用户传入的参数信息
     *
     * @param parameter 参数
     * @param point     保留小数点
     * @param msg       自定义消息
     * @return Double
     */
    public static Double getParameterToDouble(String parameter, int point, String msg) {
        // 转换成指定类型
        Double value = null;
        Object obj = null;
        Map<String, String> codeMap = Constants.setMsg(msg);
        obj = rederParmeter(getRequest(), parameter, codeMap);
        // 开启验证参数是否为空
        if (StringUtil.isEmpty(obj)) {
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }

        try {
            value = new BigDecimal(obj.toString().trim()).setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            logger.error("RequestUtil中getParameterToDouble参数转换异常", e);
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        obj = null;
        codeMap = null;
        return value;
    }

    public static BigDecimal getParameterToBigDecimal(String parameter) {
        // 转换成指定类型
        BigDecimal value = null;
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        if (obj != null) {
            try {
                if (!StringUtil.isEmpty(obj.toString().trim())) {
                    value = new BigDecimal(obj.toString().trim());
                }

            } catch (Exception e) {
                logger.error("RequestUtil中getParameterToBigDecimal参数转换异常", e);
                // 终止程序运行
                throw new ParamException(Constants.SYSTEM_CONVERT_BIGDECIMAL.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constants.SYSTEM_CONVERT_DOUBLE.get(Constants.MSG));
            }
        }
        obj = null;
        return value;
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 传入参数
     * @return
     */
    public static Date getParameterToDate(String parameter) {
        // 转换成指定类型
        Date value = null;
        Object obj = null;
        obj = rederParmeter(getRequest(), parameter);
        if (!StringUtil.isEmpty(obj)) {
            try {
                value = DateUtil.getDateTime(obj.toString());
            } catch (Exception e) {
                logger.error("RequestUtil中getParameterByDate参数转换异常", e);
                throw new ParamException(Constants.SYSTEM_CONVERT_DATE.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constants.SYSTEM_CONVERT_DATE.get(Constants.MSG));
            }
        }
        obj = null;
        return value;
    }

    /**
     * 获取用户传入参数信息
     *
     * @param parameter 传入参数
     * @param msg       为空提示信息
     * @return
     */
    public static Date getParameterToDate(String parameter, String msg) {
        // 转换成指定类型
        Date value = null;
        Object obj = null;
        Map<String, String> codeMap = Constants.setMsg(msg);
        obj = rederParmeter(getRequest(), parameter, codeMap);
        // 开启验证参数是否为空
        if (StringUtil.isEmpty(obj)) {
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        try {
            value = DateUtil.getDateTime(obj.toString());
        } catch (Exception e) {
            logger.error("RequestUtil中getParameterToDouble参数转换异常", e);
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        obj = null;
        codeMap = null;
        return value;
    }

    /**
     * 读取参数
     *
     * @return
     */
    public static Object rederParmeter(final HttpServletRequest request, String parameter) {
        Object obj = null;
        try {
           /* String data = getRequest().getParameter(Constants.DATA);
            if (StringUtil.isEmpty(data)) {
                // 未使用data参数封装
                obj = getRequest().getParameter(parameter);
            } else {
                // data参数封装
                JSONObject json = JSONObject.parseObject(data);
                if (json.containsKey(parameter)) {// 在data中查找对应key
                    obj = json.get(parameter);
                } else if (json.containsKey(Constants.PARAMS)) {// 在param中查找对应key
                    json = JSONObject.parseObject(json.get(Constants.PARAMS).toString());
                    if (json.containsKey(parameter)) {
                        obj = json.get(parameter);
                    }
                }
                json = null;
            }
            data = null;*/
            String data = (String) request.getAttribute("requestParams");
            if (data == null || "".equals(data) || data.indexOf("<xml>") == 0) {
                return null;
            } else {
                // data参数封装
                JSONObject json = JSONObject.parseObject(data);
                if (json.containsKey(parameter)) {// 在data中查找对应key
                    obj = json.get(parameter);
                } else if (json.containsKey(Constants.PARAMS)) {// 在param中查找对应key
                    json = JSONObject.parseObject(json.get(Constants.PARAMS).toString());
                    if (json.containsKey(parameter)) {
                        obj = json.get(parameter);
                    }
                }
                json = null;
                data = null;
            }
        } catch (Exception e) {
            logger.error("RequestUtil中rederParmeter异常：" + parameter, e);
            Map<String, String> codeMap = Constants.SYSTEM_GETPARAMETER;
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        return obj;
    }

    /**
     * 读取参数
     *
     * @return
     */
    public static Object rederParmeter(final HttpServletRequest request, String parameter, Map<String, String> codeMap) {
        Object obj = null;
        try {
            /*String data = getRequest().getParameter(Constants.DATA);
            if (StringUtil.isEmpty(data)) {
                // 未使用data参数封装
                obj = getRequest().getParameter(parameter);
            } else {
                // data参数封装
                JSONObject json = JSONObject.parseObject(data);
                if (json.containsKey(parameter)) {// 在data中查找对应key
                    obj = json.get(parameter);
                } else if (json.containsKey(Constants.PARAMS)) {// 在param中查找对应key
                    json = JSONObject.parseObject(json.get(Constants.PARAMS).toString());
                    if (json.containsKey(parameter)) {
                        obj = json.get(parameter);
                    }
                }
                json = null;
            }
            data = null;*/
            String method = request.getMethod();
            System.out.println(method);
            StringBuffer requestURL = request.getRequestURL();
            System.out.println(requestURL.toString());
            String localAddr = request.getLocalAddr();
            System.out.println(localAddr);
            String data = (String) request.getAttribute("requestParams");
            if (data == null || "".equals(data) || data.indexOf("<xml>") == 0) {
                return null;
            } else {
                // data参数封装
                JSONObject json = JSONObject.parseObject(data);
                if (json.containsKey(parameter)) {// 在data中查找对应key
                    obj = json.get(parameter);
                } else if (json.containsKey(Constants.PARAMS)) {// 在param中查找对应key
                    json = JSONObject.parseObject(json.get(Constants.PARAMS).toString());
                    if (json.containsKey(parameter)) {
                        obj = json.get(parameter);
                    }
                }
                json = null;
                data = null;
            }
        } catch (Exception e) {
            logger.error("RequestUtil中rederParmeter异常", e);
            // 终止程序运行
            throw new ParamException(codeMap.get(Constants.CODE) + Constants.EXCEPT_SPLIT + codeMap.get(Constants.MSG));
        }
        return obj;
    }

    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     * <p>
     * 返回的结果的Parameter名已去除前缀.
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> getParametersStartingWith(String prefix) {
        Validate.notNull(getRequest(), "Request must not be null");
        Enumeration paramNames = getRequest().getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        String pre = prefix;
        if (pre == null) {
            pre = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(pre) || paramName.startsWith(pre)) {
                String unprefixed = paramName.substring(pre.length());
                String[] values = getRequest().getParameterValues(paramName);
                if (values == null || values.length == 0) {
                    values = new String[]{};
                    // Do nothing, no values found at all.
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

    /**
     * 组合Parameters生成Query String的Parameter部分,并在paramter name上加上prefix.
     */
    public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
        StringBuilder queryStringBuilder = new StringBuilder();

        String pre = prefix;
        if (pre == null) {
            pre = "";
        }
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            queryStringBuilder.append(pre).append(entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                queryStringBuilder.append("&");
            }
        }
        return queryStringBuilder.toString();
    }

   /**
     * 客户端对Http Basic验证的 Header进行编码.
     */
    public static String encodeHttpBasic(String userName, String password) {
        String encode = userName + ":" + password;
        return "Basic " + Encodes.encodeBase64(encode.getBytes());
    }

//
//    /**
//     * 获取当前登录用户
//     *
//     * @return
//     * @author dwk 2016年1月21日上午11:57:27
//     */
//    public static TableJjrUser getLoginUser() {
//        TableJjrUser tableJjrUser = null;
//        tableJjrUser = UserCache.getUser((String) getRequest().getAttribute(Constant.USERID));
//        if (tableJjrUser == null) {
//            if ("true".equals((String) getRequest().getAttribute(Constant.ISLOGIN))) {
//                //用户必须登录，如果发现没登录或登录失效，则自动抛出异常
//                throw new ParamException(Constant.USER_NOT_LOGIN.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constant.USER_NOT_LOGIN.get(Constants.MSG));
//            } else {
//                if (null != getRequest().getAttribute(Constant.GCID) && !"".equals((String) getRequest().getAttribute(Constant.GCID))) {
//                    String userId = (String) getRequest().getAttribute(Constant.USERID);
//                    if (StringUtil.isNotBlank(userId)){
//                        TableJjrUserService userService =  SpringContextUtil.getBean("tableJjrUserService");
//                        tableJjrUser = userService.getById(userId);
//                        if (null != tableJjrUser){
//                            return tableJjrUser;
//                        }else{
//                            tableJjrUser = new TableJjrUser();
//                            tableJjrUser.setGcid(getRequest().getAttribute(Constant.GCID).toString()).setId(userId);
//                        }
//                    }else{
//                        tableJjrUser = new TableJjrUser();
//                        tableJjrUser.setGcid(getRequest().getAttribute(Constant.GCID).toString());
//                    }
//                }else{
//                    return new TableJjrUser();
//                }
//            }
//        }
//        return tableJjrUser;
//    }
//
//    /**
//     * 获取当前登录用户所属公司GCID
//     *
//     * @return
//     */
//    public static String getGcid() {
//        return getRequest().getAttribute(Constant.GCID).toString();
//    }

    /**
     * 获取Request请求参数
     * @return
     */
    public static String getRequestParams(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getRequest().getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
        }
        return null;
    }

}
