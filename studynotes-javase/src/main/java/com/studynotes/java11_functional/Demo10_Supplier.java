package com.studynotes.java11_functional;

import java.util.function.Supplier;

/**
 * Description: Supplier 用于提供数据，不接收参数，返回一个类型的值
 */
public class Demo10_Supplier {

    public static void main(String[] args) {

        Supplier<Out> supplier = Out::new;

        Out out = supplier.get();
    }

    static class Out {}
}
