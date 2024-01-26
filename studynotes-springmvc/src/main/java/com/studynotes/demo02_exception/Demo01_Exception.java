package com.studynotes.demo02_exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 模拟异常接口，使用异常处理器进行处理
 */
@RestController
@RequestMapping("/demo01_exception")
public class Demo01_Exception {

    /**
     * Description: 访问地址：`http://localhost:8090/demo02_exception/defaultBinding?name=张三`
     */
    @RequestMapping("/exception")
    public String exception(String name) {
        System.out.println("name = " + name);
        throw new RuntimeException("测试异常");
    }
}
