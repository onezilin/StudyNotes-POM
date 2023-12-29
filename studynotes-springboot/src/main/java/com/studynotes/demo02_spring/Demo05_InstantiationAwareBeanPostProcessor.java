package com.studynotes.demo02_spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Description: InstantiationAwareBeanPostProcessor 接口的实现类
 */
@Component
public class Demo05_InstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 可以通过该方法自己创建并返回 Bean 实例（即返回值不为 null），跳过 Spring 系统的 Bean 实例化过程。
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("测试 InstantiationAwareBeanPostProcessor 实例化处理，在 Bean 实例化之后、依赖注入之前执行");
        // 返回值表示是否跳过依赖注入阶段，
        // 如果返回值为 false，表示跳过依赖注入，跳过后续 InstantiationAwareBeanPostProcessor 的 postProcessAfterInstantiation()方法，跳过之后所有的
        // postProcessProperties()方法，跳过 Spring 的依赖注入。
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("测试 InstantiationAwareBeanPostProcessor 属性处理，在 Bean 实例化之后、依赖注入之前执行");
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("测试 BeanPostProcessor 前置处理，在 Bean 实例化之后、依赖注入之后执行");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("测试 BeanPostProcessor 后置处理，在 Bean 实例化之后、依赖注入之后执行");
        return bean;
    }
}
