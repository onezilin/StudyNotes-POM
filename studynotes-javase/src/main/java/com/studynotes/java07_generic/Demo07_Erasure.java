package com.studynotes.java07_generic;

import org.junit.jupiter.api.Test;

/**
 * Description: 泛型擦除带来以下限制：
 * * 不支持原始类型
 * * 不能用占位符创建实例或数组
 */
public class Demo07_Erasure<T> {

    private T a;

    private T[] array;

    @Test
    void test() {
        // a = new T(); // 不能实例化泛型对象
        // array = new T[10]; // 不能创建泛型数组
    }
}
