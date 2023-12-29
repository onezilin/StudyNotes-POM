package com.studynotes.java12_stream;

import java.util.Arrays;

/**
 * Description: Arrays.stream() 也可以将数组转换为 Stream，
 * 和 Stream.of() 类似，不过 of() 是 Stream 的 static 方法，且是可变参
 */
public class Demo11_ArraysToStream {

    public static class Machine2 {
        public static void main(String[] args) {
            Arrays.stream(new Object[]{new Object(), new Object()}).forEach((object) -> {
                System.out.println(object.toString());
            });

            Arrays.stream(new double[]{3.14159, 2.718, 1.618})
                    .forEach(n -> System.out.format("%f ", n));
            System.out.println();

            Arrays.stream(new int[]{1, 3, 5})
                    .forEach(n -> System.out.format("%d ", n));
            System.out.println();

            Arrays.stream(new long[]{11, 22, 44, 66})
                    .forEach(n -> System.out.format("%d ", n));
            System.out.println();

            // 选择一个子域:从index 为 3 到 6 进行选择
            Arrays.stream(new int[]{1, 3, 5, 7, 15, 28, 37}, 3, 6)
                    .forEach(n -> System.out.format("%d ", n));
        }
    }
}
