package com.studynotes.java07_generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Description: 在java内部会进行泛型擦除，泛型代码内部，无法获取任何有关泛型参数类型的信息
 * 泛型是使用擦除实现的，将具体类的信息擦除，所以当使用泛型 Class 对象时，任何具体的类型信息被擦除了
 */
public class Demo06_Erasure {

    @Test
    void test() {
        /**
         * getClass() 获取 Class 对象，但是泛型参数被擦除了，只能知道是 ArrayList 的 Class 对象
         */
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true
    }
}
