package com.studynotes.java11_functional;

import java.util.function.Function;

/**
 * Description: 高阶函数只是一个消费或产生函数的函数
 */
public class Demo13_HigherOrderFunction {

    /**
     * Description: 用于生成 Function 函数，produce 就称为高阶函数
     */
    static Function<Integer, String> produce() {
        return Object::toString;
    }

    /**
     * Description: 用于消费 Function 函数，consume 就称为高阶函数
     */
    static void consume(Function<Integer, String> function) {
        System.out.println(function.apply(11111111));
    }

    public static void main(String[] args) {
        consume(produce());
    }
}
