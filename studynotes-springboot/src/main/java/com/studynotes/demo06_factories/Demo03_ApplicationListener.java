package com.studynotes.demo06_factories;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Description: 自定义监听器，监听 ApplicationEvent 及其子事件
 * <p>
 * * 需要在本项目的 `META-INF/spring.factories` 中进行配置
 * * 启动项目后查看效果
 */
public class Demo03_ApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("监听到事件：" + event);
    }
}
