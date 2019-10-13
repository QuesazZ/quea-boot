package com.quesa.mybootproject.common.interceptor;

import com.quesa.mybootproject.common.constants.Constant;
import com.quesa.mybootproject.common.controller.NoAccessRequired;
import com.quesa.mybootproject.common.controller.RequestUtil;
import com.quesa.mybootproject.common.exception.ParamException;
import com.quesa.mybootproject.common.util.Constants;
import com.quesa.mybootproject.common.util.StringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //安全过滤验证
       /* String postId = RequestUtil.getParameterToString("postId");
        if(StringUtil.isEmpty(postId)){
            return false;
        }*/
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //String requestMethod = request.getServletPath().substring(request.getServletPath().lastIndexOf("/")+1);
        NoAccessRequired noAccessRequired = method.getAnnotation(NoAccessRequired.class);

        //提供API接口上有NoAccessRequired注解，表示不需要登录也可访问
        if (noAccessRequired != null) {
            /*if("f".equals(postId.charAt(8)) &&
                    "q".equals(postId.charAt(13))  &&
                    "p".equals(postId.charAt(18))  &&
                    "m".equals(postId.charAt(23))  &&
                    "s".equals(postId.charAt(14))){
                //验证成功，继续执行
            }else{
                return false;
            }*/
            return true;
        } else {
//            User user = UserCache.getUser(RequestUtil.getParameterToString(Constant.USERID));
////            //验证失败
//            if (StringUtil.isEmpty(user) || user.getToken() == null || !user.getToken().equals(RequestUtil.getParameterToString(Constant.TOKEN))) {
////                throw new ParamException(Constant.setThrowableMsg(Constant.USER_NOT_LOGIN.get(Constant.MSG)));
//                throw new ParamException(Constant.USER_NOT_LOGIN.get(Constants.CODE) + Constants.EXCEPT_SPLIT + Constant.USER_NOT_LOGIN.get(Constants.MSG));
//            }

        }
        httpServletRequest.setAttribute(Constant.ISLOGIN, "true");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
