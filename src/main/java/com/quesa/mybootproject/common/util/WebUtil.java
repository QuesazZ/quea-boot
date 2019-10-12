package com.quesa.mybootproject.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Web层辅助类
 *
 * @author ShenHuaJie
 * @version 2016年4月2日 下午4:19:28
 */
public final class WebUtil {
    private WebUtil() {
    }

    /**
     * 日志对象
     */
    protected static Logger logger = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 获取指定Cookie的值
     *
     * @param cookieName   cookie名字
     * @param defaultValue 缺省值
     * @return
     */
    public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        if (cookie == null) {
            return defaultValue;
        }
        return cookie.getValue();
    }

    /**
     * 获得国际化信息
     *
     * @param key     键
     * @param request
     * @return
     */
    public static final String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    /**
     * 获得参数Map
     *
     * @param request
     * @return
     */
    public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, null);
    }

    /**
     * 获取客户端IP
     */
    public static final String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(ip)) {
            InetAddress inet = null;
            try { // 根据网卡取本机配置的IP
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ip = inet.getHostAddress();
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    public static final Integer check(HttpServletRequest request) {
        System.out.println("获取来源-------------------------------------------------");
        logger.info("获取来源-------------------------------------------------");
        Integer isFromMobile = 2;
        //检查是否已经记录访问方式（移动端或pc端）
        try {
            //获取ua，用来判断是否为移动端访问
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            System.out.println("userAgent--------------------------------------:      " + userAgent);
            logger.info("userAgent--------------------------------------:      " + userAgent);
            if (null == userAgent) {
                userAgent = "";
            }
            isFromMobile = CheckMobile.check(userAgent);
        } catch (Exception e) {
        }
        return isFromMobile;
    }

    public static String getRequestURITail(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String servletPath = request.getServletPath();
        int index = servletPath.lastIndexOf("/");
        String tail = servletPath.substring(index + 1);
        return  tail;
    }
}
