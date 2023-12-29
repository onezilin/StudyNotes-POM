package com.studynotes.java12_stream;

import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

/**
 * Description: IntStream.range() 用于生成指定范围中，步长为 1 的基本类型顺序的 IntStream 流
 * <p>
 * IntStream.rangeClosed() 用于生成指定范围中，步长为 1 的基本类型顺序的 IntStream 流，包含上限
 * <p>
 * sum() 用于计算流中元素的和
 */
public class Demo06_IntStreamRange {
    public static void main(String[] args) {
        // 1、传统方法:
        int result = 0;
        for (int i = 10; i < 20; i++)
            result += i;
        System.out.println(result);

        // 2、for-in 循环:
        result = 0;
        for (int i : IntStream.range(10, 20).toArray())
            result += i;
        System.out.println(result);

        // 3、使用流:
        System.out.println(range(10, 20).sum());
    }
}
