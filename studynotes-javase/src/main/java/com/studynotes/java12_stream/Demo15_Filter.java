package com.studynotes.java12_stream;

import java.util.stream.LongStream;

import static java.util.stream.LongStream.iterate;
import static java.util.stream.LongStream.rangeClosed;

/**
 * Description: filter() 用于过滤流中的元素，保留filter返回值为true的元素
 */
public class Demo15_Filter {
    // 是否是素数
    public static Boolean isPrime(long n) {
        // range是左闭右开，rangClosed是左闭右闭
        return rangeClosed(2, (long) Math.sqrt(n))
                // 将流中每个元素进行Predicate计算，若都是false，则返回true
                .noneMatch(i -> n % i == 0);
    }

    public LongStream numbers() {
        return iterate(2, i -> i + 1)
                .filter(Demo15_Filter::isPrime);
    }

    public static void main(String[] args) {
        new Demo15_Filter().numbers()
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));

        new Demo15_Filter().numbers()
                .skip(90)
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
    }
}
