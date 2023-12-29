package com.studynotes.demo01_spring;

import com.studynotes.bean.CustomBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Description: 通过 ApplicationContext 注册并管理 Bean 类
 * <p>
 * - ClassPathXmlApplicationContext 是从 classpath 路径下获取配置文件。
 * - FileSystemXmlApplicationContext 是从文件系统中获取配置文件。
 */
public class Demo02_ApplicationContext {

    @Test
    public void test() {
        ApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("spring.xml");
        CustomBean bean1 = applicationContext1.getBean("customBean", CustomBean.class);
        System.out.println(bean1);

        ApplicationContext applicationContext2 = new FileSystemXmlApplicationContext("classpath:spring.xml");
        CustomBean bean2 = applicationContext2.getBean("customBean", CustomBean.class);
        System.out.println(bean2);
    }
}
