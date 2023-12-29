package com.studynotes.java07_generic;

import java.util.List;

/**
 * Description: 探讨泛型和通配符的区别：
 *
 */
public class Demo15_Difference {

    /**
     * 在这种写法下，两者并没有什么区别
     */
    <T extends Number> void test1(List<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    void test2(List<? extends Number> list) {
        for (Number t : list) {
            System.out.println(t);
        }
    }

    /**
     * 当使用两个泛型时，就会有区别了，其中 list、list2 是相同的泛型
     */
    <T extends Number> void test3(List<T> list, List<T> list2) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    /**
     * 使用通配符是，list、list2 是不同的泛型
     */
    void test4(List<? extends Number> list, List<? extends Number> list2) {
        for (Number t : list) {
            System.out.println(t);
        }
    }
}
