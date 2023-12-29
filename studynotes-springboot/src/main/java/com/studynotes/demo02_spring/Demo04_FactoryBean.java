package com.studynotes.demo02_spring;

import com.studynotes.bean.CustomBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Description: 实现 FactoryBean 接口，可以自定义 Bean 实例
 */
@Component
public class Demo04_FactoryBean implements FactoryBean<CustomBean> {

    @Override
    public CustomBean getObject() {
        return new CustomBean();
    }

    @Override
    public Class<?> getObjectType() {
        return CustomBean.class;
    }
}
