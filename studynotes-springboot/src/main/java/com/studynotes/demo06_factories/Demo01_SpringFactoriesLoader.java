package com.studynotes.demo06_factories;

import org.springframework.context.ApplicationListener;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.List;

/**
 * Description: Spring Factories 机制，用于加载 META-INF/spring.factories 中的配置
 */
public class Demo01_SpringFactoriesLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

        // 从 META-INF/spring.factories 中加载所有的 ApplicationListener
        List<String> list = SpringFactoriesLoader.loadFactoryNames(ApplicationListener.class, classLoader);
        list.forEach(System.out::println);
    }
}
