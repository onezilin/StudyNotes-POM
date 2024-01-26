package com.studynotes.demo02_exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 实现 HandlerExceptionResolver 接口，自定义异常处理器，用于处理 Controller 层抛出的异常
 */
@Component
@Slf4j
public class Demo02_HandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        log.error("处理器信息：" + handler);
        log.error("异常信息：", ex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error"); // 设置异常处理后跳转的页面
        return modelAndView;
    }
}
