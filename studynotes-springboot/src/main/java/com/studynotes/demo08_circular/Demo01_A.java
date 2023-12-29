package com.studynotes.demo08_circular;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Description: 验证 【AOP、循环依赖】发生的情况下，第三级缓存的作用，去查看源码中的 getEarlyBeanReference 方法
 * <p>
 * 结论：第三级缓存的目的是为了延迟代理对象的创建
 */
@Aspect
@Component
public class Demo01_A {

    @Resource
    private Demo02_B b;

    @PostConstruct
    public void init() {
        System.out.println("Demo01_A init");
    }
}
