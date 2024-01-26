package com.studynotes.demo03_interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 拦截器拦截的请求接口
 */
@RestController
@RequestMapping("/demo01_interceptor")
public class Demo01_Interceptor {

    @GetMapping("/interceptor")
    public String interceptor(String name) {
        System.out.println("name = " + name);
        return "interceptor";
    }
}
