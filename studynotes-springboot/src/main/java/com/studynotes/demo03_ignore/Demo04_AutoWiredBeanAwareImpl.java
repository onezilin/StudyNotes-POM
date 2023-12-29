package com.studynotes.demo03_ignore;

import lombok.Getter;

/**
 * Description: 通过 spring.xml 中的 default-autowire="byType"，实现自动注入 autoWiredBean1 和 autoWiredBean2
 */
public class Demo04_AutoWiredBeanAwareImpl implements Demo03_AutoWiredBeanAware {

    @Getter
    private Demo01_AutoWiredBean autoWiredBean1;

    @Getter
    private Demo02_AutoWiredBean autoWiredBean2;

    /**
     * Description: 自动装配需要提供 setter 方法
     */
    @Override
    public void setAutoWiredBean(Demo01_AutoWiredBean autoWiredBean) {
        this.autoWiredBean1 = autoWiredBean;
    }

    public void setAutoWiredBean(Demo02_AutoWiredBean autoWiredBean) {
        this.autoWiredBean2 = autoWiredBean;
    }
}
