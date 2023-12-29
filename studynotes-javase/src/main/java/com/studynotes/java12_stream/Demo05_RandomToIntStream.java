package com.studynotes.java12_stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Description: Random 提供 ints()、longs()、doubles() 方法，可以将其转换为 IntStream、LongStream、DoubleStream
 * <p>
 * boxed() 方法将基本类型的流转换为装箱后的 Stream 流
 */
public class Demo05_RandomToIntStream {
    public static <T> void show(Stream<T> stream) {
        stream.limit(4).forEach(System.out::println);
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        // 0 个参数是无限个的不限上下限的随机数
        // 1 个参是指定随机数个数，
        // 2 个参是指定上下限，
        // 3 个参是指定随机数个数和上下限，boxed是将基本类型自动装箱成对象类型随机数
        show(rand.ints().boxed());

        show(rand.longs().boxed());

        show(rand.doubles(20, 30).boxed());

        show(rand.ints(2).boxed());

        show(rand.ints(2, 10, 20).boxed());
    }
}
