package com.studynotes.demo02_spring;

import lombok.Getter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description: 证明 applicationContext、resourceLoader 都指向同一个 ApplicationContext 对象
 */
@Component
public class Demo06_ResourceLoader {

    @Getter
    private ApplicationContext applicationContext;

    @Getter
    private BeanFactory beanFactory;

    @Getter
    private ResourceLoader resourceLoader;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        System.out.println("applicationContext == beanFactory: " + (applicationContext == beanFactory)); // false
        System.out.println("applicationContext == resourceLoader: " + (applicationContext == resourceLoader)); // true
    }
}
