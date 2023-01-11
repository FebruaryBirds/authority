package com.common.verify.service;

import com.common.verify.annotation.AuthPassPort;
import com.common.verify.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if (excludes(url)) {
            return true;
        }
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }
        if("/error".equals(url)) {
            return super.preHandle(request, response, handler);
        }
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod h = (HandlerMethod) handler;
            AuthPassPort authPassport = h.getMethod().getDeclaringClass().getAnnotation(AuthPassPort.class);
            // 登录验证
            if (authPassport != null && authPassport.value() == true){
                //如果不需要登录就需要访问到方法，则需要方法前面加	@AuthPassport
                return true;
            }
            authPassport = h.getMethodAnnotation(AuthPassPort.class);
            if (authPassport != null && authPassport.value() == true){
                //如果不需要登录就需要访问到方法，则需要方法前面加	@AuthPassport
                return true;
            }

            String token = request.getHeader("Token");
            if(token==null || token.equals("")){
                token = request.getParameter("Token");
            }
            boolean valid = JwtUtil.verifyToken(token);
            if (valid){
                return true;
            }else {
                rendError(response);
                return false;
            }
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private void rendError(HttpServletResponse response) throws Exception{
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"status\":401,\"message\":\"没有登录！\"}");
    }

    private boolean excludes(String url){
        String[] paths = {"/","/error","/swagger-ui.html","/swagger-resources/**","/v3/api-docs","/webjars/springfox-swagger-ui/**"};
        PathMatcher matcher = new AntPathMatcher();
        for(String path : paths){
            if (matcher.match(path, url)){
                return true;
            }
        }
        return false;
    }
}
