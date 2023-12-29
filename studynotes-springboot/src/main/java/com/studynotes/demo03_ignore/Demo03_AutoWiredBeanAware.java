package com.studynotes.demo03_ignore;

import org.springframework.beans.BeansException;

/**
 * Description: Aware 接口，用于获取 Demo01_AutoWiredBean
 */
public interface Demo03_AutoWiredBeanAware {

    void setAutoWiredBean(Demo01_AutoWiredBean autoWiredBean) throws BeansException;
}
