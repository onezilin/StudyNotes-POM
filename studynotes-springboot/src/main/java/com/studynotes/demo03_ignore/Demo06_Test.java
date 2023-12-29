package com.studynotes.demo03_ignore;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: 测试 ignoreDependencyInterface 方法的作用
 */
public class Demo06_Test {

    /**
     * Description:
     * 测试步骤：
     * 1. 直接测试，查看效果；
     * 2. 注释掉 Demo05_IgnoreBeanFactoryPostProcessor 中 ignoreDependencyInterface，查看效果
     * 预期：
     * 1. 控制台打印出 null 和 Demo02_AutoWiredBean
     * 2. 控制台打印出 Demo01_AutoWiredBean 和 Demo02_AutoWiredBean
     * <p>
     * 结论：
     * 在 default-autowire 生效的情况下，ignoreDependencyInterface 方法的作用是【忽略指定接口中方法的自动注入】
     */
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring.xml");
        Demo04_AutoWiredBeanAwareImpl demo03 = applicationContext.getBean("autoWiredBeanAware",
                Demo04_AutoWiredBeanAwareImpl.class);

        System.out.println(demo03.getAutoWiredBean1());
        System.out.println(demo03.getAutoWiredBean2());
    }
}
