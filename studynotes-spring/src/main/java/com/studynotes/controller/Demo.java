package com.studynotes.controller;

import com.studynotes.dao.UserDao;
import com.studynotes.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Description:
 */
@Controller
@Slf4j
public class Demo {

    @Resource
    private UserDao userDao;

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

    @GetMapping("/queryById/{id}")
    @ResponseBody
    public User queryById(@PathVariable("id") Long id) {
        log.info("id: {}", id);
        return userDao.queryById(id);
    }
}
