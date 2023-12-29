package com.studynotes.demo06_factories;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

/**
 * Description: 自定义 BootstrapRegistryInitializer 实现类，注册类到 BootstrapRegistry 中
 * <p>
 * * 需要在本项目的 `META-INF/spring.factories` 中进行配置
 * * 在 Demo04_ApplicationListener 监听器中可以获取到注册的类
 */
public class Demo02_BootstrapRegistryInitializer implements BootstrapRegistryInitializer {

    @Override
    public void initialize(BootstrapRegistry registry) {
        System.out.println("Demo02_BootstrapRegistry.initialize");
        registry.register(Demo02_BootstrapRegistryInitializer.class,
                (context) -> new Demo02_BootstrapRegistryInitializer());
    }
}

