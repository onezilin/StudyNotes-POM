package com.studynotes.java11_functional;

import java.util.function.Predicate;

/**
 * Description: Predicate 用于判断，接收参数，返回 boolean 值
 */
public class Demo12_Predicate {

    public static void main(String[] args) {
        Predicate<Integer> predicate = (i) -> {
            System.out.println(i);
            return i > 0;
        };
    }
}
