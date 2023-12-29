package com.studynotes.java07_generic;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Description: 上界通配符
 */
public class Demo14_WildcardGeneric {

    @Test
    void test(List<? super MyClass> list) {
        Object o = list.get(0); // 只能获取Object类型
        list.add(new MyClass()); // 可以添加MyClass及其子类
        list.add(new MyClassSon());
    }

    interface MyInterface1 {
    }

    interface MyInterface2 {
    }

    static class MyClass implements MyInterface1, MyInterface2 {
    }

    static class MyClassSon extends MyClass {
    }
}

