package com.qst.atbtmusic.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object user=httpServletRequest.getSession().getAttribute("user");
        if (user==null){
            //登陆失败
            httpServletRequest.setAttribute("msg","未登录，没有权限进行访问");
            httpServletRequest.getRequestDispatcher("/user/login").forward(httpServletRequest,httpServletResponse);
            return false;
        }else{
            //登陆成功
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
