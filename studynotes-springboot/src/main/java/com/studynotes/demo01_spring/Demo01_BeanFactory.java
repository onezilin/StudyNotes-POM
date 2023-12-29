package com.studynotes.demo01_spring;

import com.studynotes.bean.CustomBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Description: 通过 BeanFactory 注册并管理 Bean 类
 */
public class Demo01_BeanFactory {

    /**
     * Description: BeanFactory 通过 XML 配置文件方式创建 Bean
     */
    @Test
    public void test1() {
        BeanFactory beanFactory1 = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        CustomBean bean = beanFactory1.getBean("customBean", CustomBean.class);
        System.out.println(bean);
    }

    /**
     * Description: BeanFactory 通过 API 方式创建 Bean
     */
    @Test
    public void test2() {
        DefaultListableBeanFactory beanFactory2 = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = new RootBeanDefinition(CustomBean.class);
        beanFactory2.registerBeanDefinition("customBean", beanDefinition);
        CustomBean bean2 = beanFactory2.getBean("customBean", CustomBean.class);
        System.out.println(bean2);
    }
}
