package com.studynotes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 */
@Controller
@Slf4j
public class Demo {

    @GetMapping("/login")
    public String login(String name) {
        log.info("name: {}", name);
        return "login"; // 跳转到 login 页面
    }

    @GetMapping("/loginMessage")
    @ResponseBody
    public String loginMessage(String name) {
        log.info("name: {}", name);
        return "login"; // 返回 login 字符串
    }
}
