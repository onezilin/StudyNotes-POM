package com.studynotes.demo01_handler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: URL 请求路径中的参数绑定
 */
@RestController
@RequestMapping("/demo01_binding")
@Slf4j
public class Demo01_Binding {

    /**
     * Description: 在形参中添加如下类型的参数，会默认识别并进行赋值：HttpServletRequest，HttpServletResponse，ModelAndView
     * <p>
     * 访问地址：`http://localhost:8090/demo01_bingding/defaultBinding?name=张三`
     */
    @GetMapping("/defaultBinding")
    public String defaultBinding(String name, HttpServletRequest request, HttpServletResponse response,
                                 ModelAndView modelAndView) {
        log.info("name = " + name);
        log.info("request = " + request);
        log.info("response = " + response);
        log.info("modelAndView = " + modelAndView); // ModelAndView [view=[null]; model=null]

        return "Hello World!";
    }

    /**
     * Description: 将 URL 请求路径中的参数绑定到形参
     * <p>
     * 访问地址：`http://localhost:8090/demo01_bingding/simpleBinding?name=张三&age=20`
     */
    @GetMapping("/simpleBinding")
    public String simpleBinding(String name, int age) {
        log.info("name = " + name);
        log.info("age = " + age);

        return "Hello World!";
    }

    /**
     * Description: 将 URL 请求路径中的参数绑定到形参的 POJO 类中的属性
     * <p>
     * 访问地址：`http://localhost:8090/demo01_bingding/pojoBinding?name=张三&age=20`
     */
    @GetMapping("/pojoBinding")
    public String pojoBinding(User user) {
        log.info("name = " + user.getName());
        log.info("age = " + user.getAge());

        return "Hello World!";
    }

    @Data
    public static class User {

        private String name;

        private int age;
    }
}
