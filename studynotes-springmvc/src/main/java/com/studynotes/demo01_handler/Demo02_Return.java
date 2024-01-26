package com.studynotes.demo01_handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: SpringMVC 底层提供一系列的 HandlerMethodReturnValueHandler 实现类，用于将处理器返回值封装为 ModelAndView 类型
 */
@Controller
@RequestMapping("/demo02_return")
public class Demo02_Return {

    /**
     * Description: 访问地址：`http://localhost:8090/demo02_annotation/default?name=张三`
     */
    @RequestMapping("/login")
    public String login(String name) {
        System.out.println("name = " + name);

        return "login";
    }

    /**
     * Description: 被 @ResponseBody 注解的方法，返回值不会被封装为 ModelAndView 类型，而是直接返回给浏览器
     * <p>
     * 访问地址：`http://localhost:8090/demo02_annotation/default?name=张三`
     */
    @RequestMapping("/loginReturnString")
    @ResponseBody
    public String loginReturnString(String name) {
        System.out.println("name = " + name);

        return "login";
    }
}
