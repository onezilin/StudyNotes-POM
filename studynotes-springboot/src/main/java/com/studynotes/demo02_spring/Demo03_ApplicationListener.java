package com.studynotes.demo02_spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description: 实现 ApplicationListener 接口，监听 Spring 容器中发布的事件
 */
@Component
public class Demo03_ApplicationListener implements ApplicationListener<ApplicationEvent> {

    public Demo03_ApplicationListener() {
        System.out.println("监听器初始化...");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("监听事件：" + event);
    }
}
