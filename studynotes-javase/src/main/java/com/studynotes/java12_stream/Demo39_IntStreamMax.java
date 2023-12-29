package com.studynotes.java12_stream;

import java.util.stream.Stream;

import static com.studynotes.java12_stream.Demo31_ToArray.rands;

/**
 * Description: 对数值流的操作，获取最大值，最小值，平均值，求和，统计值
 */
public class Demo39_IntStreamMax {
    public static void main(String[] args) {
        System.out.println(rands().average().getAsDouble());
        System.out.println(rands().max().getAsInt());
        System.out.println(rands().min().getAsInt());
        System.out.println(rands().sum());
        System.out.println(rands().summaryStatistics());


        Stream<Object> stream = Stream.of(new Bubble(1), "11111", new Bubble(2), new Bubble(3));
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }
}
