package com.qd.common.interceptor;


import com.qd.common.utils.EmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 允许跨域的主机地址
//        response.setHeader("Access-Control-Allow-Origin", "*");
        // 地址过滤
        String uri = request.getRequestURI();
        log.info("执行了拦截器，请求地址是"+uri);
        if (uri.contains("/login") || uri.contains("/error")) {
            log.info("请求地址是"+uri+",放行");
            return true; // 放行
        }

        //检查登录
        HttpSession session = request.getSession();
        Object userName = session.getAttribute("userName");
        if(EmptyUtils.isNotEmpty(userName)){
            log.info(userName+"已经登录，放行");
            return true; // 放行
        }


        // 设置状态码为302表示临时重定向，或者你可以使用response.sendRedirect("xxx.jsp");
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        // 设置重定向的目标页面
        response.setHeader("location", "/admin/login.html");
        return false; // 不放行
    }
}