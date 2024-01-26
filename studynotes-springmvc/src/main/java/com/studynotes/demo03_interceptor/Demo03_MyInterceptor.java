package com.studynotes.demo03_interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 实现 HandlerInterceptor 接口，自定义拦截器
 */
public class Demo03_MyInterceptor implements HandlerInterceptor {

    /**
     * Description: 处理器执行之前执行，如果返回 false 将跳过处理器、拦截器 postHandle 方法、视图渲染等，直接执行拦截器 afterCompletion 方法。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle2");
        return false;
    }

    /**
     * Description: 处理器执行后，视图渲染前执行，如果处理器抛出异常，将跳过该方法直接执行拦截器 afterCompletion 方法。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle2");
    }

    /**
     * Description: 视图渲染后执行，不管处理器是否抛出异常，该方法都将执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        System.out.println("afterCompletion2");
    }
}
