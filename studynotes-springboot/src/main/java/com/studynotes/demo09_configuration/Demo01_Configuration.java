package com.studynotes.demo09_configuration;

import com.studynotes.bean.CustomBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Description: @Configuration 注解的类被 CGLIB 增强，会设置一个拦截器专门对 @Bean 方法进行拦截处理，
 * 通过依赖查找的方式从 IoC 容器中获取 Bean 对象，**如果是单例 Bean，那么每次都是返回同一个对象**。
 */
@Configuration
public class Demo01_Configuration {

    @Bean("customBeanTwo")
    public CustomBean customBean() {
        return new CustomBean();
    }

    @PostConstruct
    public void init() {
        System.out.println("Demo09_Configuration init");
        CustomBean customBean1 = customBean();
        CustomBean customBean2 = customBean();
        System.out.println("customBean1 == customBean2: " + (customBean1 == customBean2)); // true
    }
}
