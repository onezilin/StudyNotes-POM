package com.studynotes.demo02_exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 使用 @ControllerAdvice 注解，实现全局异常处理器，用于处理 Controller 层抛出的异常，@ExceptionHandler 注解用于处理指定异常处理方法
 */
@RestControllerAdvice
public class Demo03_HandlerExceptionResolver {

    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(RuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("request：" + request);
        System.out.println("response：" + response);
        System.out.println("异常信息：" + e.getMessage());
        return "error"; // 返回值
    }
}
