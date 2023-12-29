package com.studynotes.java10_reflect;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * Description: 类的 Class 对象获取及使用
 */
public class Demo01_Class {

    interface MyInterface {
    }

    static class Father {
    }

    static class Son extends Father implements MyInterface {
    }

    @Test
    @SneakyThrows
    void test() {
        Class<Son> clazz = Son.class;

        System.out.println("全限定类名：" + clazz.getName());

        System.out.println("标准全限定类型：" + clazz.getCanonicalName());

        System.out.println("类名：" + clazz.getSimpleName());

        // 调用无参构造方法，创建对象
        Son user = clazz.newInstance();

        // 获取父类的 Class 对象
        Class<? super Son> fatherClazz = clazz.getSuperclass();

        // 获取实现的接口的 Class 对象数组
        Class<?>[] interfaceClazz = clazz.getInterfaces();
    }
}
