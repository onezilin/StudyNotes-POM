package com.studynotes.demo06_factories;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Description: 自定义监听器，监听 ApplicationStartingEvent 事件，用于注册在 BootstrapRegistry 中的类
 * <p>
 * * 需要在本项目的 `META-INF/spring.factories` 中进行配置
 * * 启动项目后查看效果
 */
public class Demo04_ApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("监听到事件：" + event);
        ConfigurableBootstrapContext bootstrapContext = event.getBootstrapContext();
        Demo02_BootstrapRegistryInitializer demo = bootstrapContext.get(Demo02_BootstrapRegistryInitializer.class);
        System.out.println("BootstrapRegistryInitializer：" + demo);
    }
}
