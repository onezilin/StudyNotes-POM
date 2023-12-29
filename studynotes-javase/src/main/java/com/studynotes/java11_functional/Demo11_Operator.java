package com.studynotes.java11_functional;

import java.util.function.UnaryOperator;

/**
 * Description: 如果返回值类型和参数类型相同，则是一个Operator
 */
public class Demo11_Operator {

    public static void main(String[] args) {
        UnaryOperator<Integer> unaryOperator = (i) -> {
            System.out.println(i);
            return i;
        };
    }
}
