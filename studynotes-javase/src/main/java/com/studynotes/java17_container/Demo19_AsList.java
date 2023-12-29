package com.studynotes.java17_container;

import java.util.Arrays;

public class Demo19_AsList {
    static <T> void test(Iterable<T> ib) {
        for (T t : ib) System.out.print(t + " ");
    }

    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 3));
        String[] strings = {"A", "B", "C"};
        // An array works in for-in, but it's not Iterable:
        // 尝试将数组作为一个Iterable参数传递会报错,说明数组不是Itrable,并且也不会自动转换为Iterable
        //		  test(strings);
        // You must explicitly convert it to an Iterable:
        test(Arrays.asList(strings));
    }
}
