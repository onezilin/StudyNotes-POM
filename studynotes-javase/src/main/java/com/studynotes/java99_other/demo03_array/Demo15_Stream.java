package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

// 流可以和数组很好的融合,
// 只有int long double 可以直接使用Arrays.stream()获取IntStream LongStream DoubleStream流,
// 其他的几个基本数据类型需要转化为封装类才可以使用
public class Demo15_Stream {
    public static void main(String[] args) {
        String[] s = (String[]) Stream.of("1", "2", "3").toArray();
        Arrays.stream(s).skip(3).limit(5).map(ss -> ss + "!").forEach(System.out::println);
        int[] a = IntStream.range(0, 10).toArray();
        Arrays.stream(a)
                .skip(3)
                .limit(5)
                .map(i -> i * 10).forEach(System.out::println);
        LongStream longStream = Arrays.stream(new long[10]);
        DoubleStream doubleStream = Arrays.stream(new double[10]);
        // 只有 int, long and double 数组能转换为IntStream LongStream DoubleStream基本类型流:
        // - Arrays.stream(new boolean[10]);
        // - Arrays.stream(new byte[10]);
        // - Arrays.stream(new char[10]);
        // - Arrays.stream(new short[10]);
        // - Arrays.stream(new float[10]);
    }
}
