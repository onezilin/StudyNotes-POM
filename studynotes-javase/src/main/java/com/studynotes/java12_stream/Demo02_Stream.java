package com.studynotes.java12_stream;

import java.util.Random;

/**
 * Description: 转换为 Stream 流后进行遍历，流是声明式编程——声明要做什么，而不是怎么做
 * <p>
 * distinct() 可以消除流中的重复元素
 */
public class Demo02_Stream {
    public static void main(String[] args) {

        new Random(47)
                .ints(5, 20)
                .distinct()
                .limit(1)
                .sorted()
                .forEach(System.out::println);
    }
}
