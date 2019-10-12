package com.quesa.mybootproject.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.quesa.mybootproject.common.controller.NoAccessRequired;
import com.quesa.mybootproject.common.controller.RequestUtil;
import com.quesa.mybootproject.common.util.HttpHelper;
import com.quesa.mybootproject.common.constants.Constant;
import com.quesa.mybootproject.common.util.SignKeyUitl;
import com.quesa.mybootproject.common.util.StringUtil;
import com.quesa.mybootproject.config.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 */
public class RequestInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("ThreadLocal StartTime");
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理器实际执行之前
//        logger.error("+++处理器执行之前++");
        if (request.getContentType() != null && request.getContentType() != "") {
            if (request.getContentType().contains("multipart/form-data")) {
                //form表单文件上传
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userid", request.getParameter("userid"));
                jsonObject.put("token", request.getParameter("token"));
                request.setAttribute("requestParams", jsonObject.toJSONString());//获取请求内容
            } else if ("file".equals(request.getHeader("Request-Type"))) {
                //file文件上传
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userid", request.getHeader("userid"));
                jsonObject.put("token", request.getHeader("token"));
                request.setAttribute("requestParams", jsonObject.toJSONString());//获取请求内容
            } else if ("body".equals(request.getHeader("Request-Type")) || StringUtil.isEmpty(request.getParameter(Constant.DATA))) {
                //body方式请求
                request.setAttribute("requestParams", HttpHelper.getBodyString(request));//获取body请求内容
                //request.setAttribute(Constant.USERID, request.getHeader(Constant.USERID));//保存当前用户ID到request中
            } else {
                //parameter方式请求
                request.setAttribute("requestParams", request.getParameter(Constant.DATA));//获取请求参数内容
            }
        } else {
            if ("file".equals(request.getHeader("Request-Type"))) {
                //file文件上传
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userid", request.getHeader("userid"));
                jsonObject.put("token", request.getHeader("token"));
                request.setAttribute("requestParams", jsonObject.toJSONString());//获取请求内容
            } else if ("body".equals(request.getHeader("Request-Type")) || StringUtil.isEmpty(request.getParameter(Constant.DATA))) {
                //body方式请求
//                request.setAttribute("requestParams", HttpHelper.getBodyString(request));//获取body请求内容
                String reqstring = HttpHelper.getBodyString(request);
                JSONObject json = null;
                try {
                    json = JSONObject.parseObject(reqstring);
                }catch (Exception e){

                }
                SignKeyUitl sku = SpringContextUtil.getBean("signKeyUitl");
                if (null != json && StringUtil.isNotBlank(json.getString("signkey"))) {
                    /*if(SignKeyUitl.checkDateKey(json.get("signkey").toString(),sku.getApartment(),json.get("params").toString())){
                        request.setAttribute("requestParams", reqstring);
                    }else{
                        return false;
                    }*/
                    request.setAttribute("requestParams", reqstring);
                } else {
                    request.setAttribute("requestParams", reqstring);
                }

                //request.setAttribute(Constant.USERID, request.getHeader(Constant.USERID));//保存当前用户ID到request中
            } else {
                //parameter方式请求
//                request.setAttribute("requestParams", request.getParameter(Constant.DATA));//获取请求参数内容
                String reqstring = request.getParameter(Constant.DATA);
                try {
                    JSONObject json = JSONObject.parseObject(reqstring);
                    SignKeyUitl sku = SpringContextUtil.getBean("signKeyUitl");
                    if (null != json && StringUtil.isNotBlank(json.getString("signkey"))) {
                    /*if(SignKeyUitl.checkDateKey(json.get("signkey").toString(),sku.getApartment(),json.get("params").toString())){
                        request.setAttribute("requestParams", reqstring);
                    }else{
                        return false;
                    }*/
                        request.setAttribute("requestParams", reqstring);
                    } else {
                        request.setAttribute("requestParams", reqstring);
                    }
                }catch (Exception e){
                    logger.error("+++parameter方式请求+cache+");
                    request.setAttribute("requestParams", reqstring);
                }
            }
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        NoAccessRequired noAccessRequired = method.getAnnotation(NoAccessRequired.class);
        if (noAccessRequired != null && "payType".equals(method.getName())) {
        } else {
            request.setAttribute(Constant.USERID, RequestUtil.getParameterToString(Constant.USERID));//保存当前用户ID到request中
        }
        /*
        request.setAttribute(Constant.USERID, RequestUtil.getParameterToString(Constant.USERID));//保存当前用户ID到request中
        request.setAttribute(Constant.GCID, RequestUtil.getParameterToString(Constant.GCID));//保存当前公司ID到request中
        */
        /*if (logger.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }*/
        /*HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //String requestMethod = request.getServletPath().substring(request.getServletPath().lastIndexOf("/")+1);
        //如果请求方法为ui,不做任何处理
        if ("swaggerResources".equals(method.getName())) {
            return true;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelandView) throws Exception {
        // 处理器执行完毕之后
        if (modelandView != null) {
            logger.info("ViewName: " + modelandView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }


}
