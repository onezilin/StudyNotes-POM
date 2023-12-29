package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;
import java.util.stream.LongStream;

// 使用普通的流处理数组终端的数据
// 使用parallelSetAll设置数组中元素
public class Demo12_ParallelSetAll {
    static long[] fillCounted(int size) {
        return LongStream.iterate(0, i -> i + 1).limit(size).toArray();
    }

    static void intArray(int size) {
        int[] a = new int[size];
        Arrays.setAll(a, n -> n);
        // 内部使用流编程中的parallel方法,并行处理数据
        Arrays.parallelSetAll(a, n -> n);
    }

    static void longArray(int size) {
        long[] a = new long[size];
        Arrays.setAll(a, n -> n);
        Arrays.parallelSetAll(a, n -> n);
    }

    public static void main(String[] args) {
        long[] a = fillCounted(20); // No problem
        System.out.println("a:" + Arrays.toString(a));

        long[] b = fillCounted(10_000_000_00);
        System.out.println("b:" + Arrays.toString(b));

        intArray(10);
        longArray(10);
    }
}
