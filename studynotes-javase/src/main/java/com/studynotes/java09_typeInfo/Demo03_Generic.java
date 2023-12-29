package com.studynotes.java09_typeInfo;

import org.junit.jupiter.api.Test;

/**
 * Description: 使用泛型的 Class 对象
 */
public class Demo03_Generic {

    @Test
    void test1() {
        /**
         * 没有使用泛型的 Class 对象，可以指向任何类型的 Class 对象
         */
        Class intClass = int.class;
        intClass = Integer.class; // 指向 Integer.class
        intClass = double.class;
    }

    void test2() {
        /**
         * 使用泛型的 Class 对象，只能指向指定类型的 Class 对象
         */
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class; // 指向 Integer.class
        /**
         * 不能将 Class<Double> 赋值给 Class<Integer>，因为泛型不具备协变性，编译报错
         */
        // genericIntClass = double.class;
    }

    void test3() {
        /**
         * extends Number 表示泛型参数的上界，即泛型参数必须是 Number 或 Number 的子类
         */
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
    }
}

