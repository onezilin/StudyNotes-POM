package com.studynotes.demo03_ignore;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Description: BeanFactoryPostProcessor 实现类，测试 ignoreDependencyInterface 方法作用
 * <p>
 * 在 default-autowire 生效的情况下，ignoreDependencyInterface 方法的作用是【忽略指定接口中方法的自动注入】
 * 例如：{@link Demo03_AutoWiredBeanAware#setAutoWiredBean(Demo01_AutoWiredBean)} 方法，不会执行该方法的自动注入
 */
public class Demo05_IgnoreBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.ignoreDependencyInterface(Demo03_AutoWiredBeanAware.class);
    }
}
