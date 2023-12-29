package com.studynotes.java12_stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Description: toArray() 用来将 Stream 转换为对应类型的数组
 */
public class Demo31_ToArray {
    private static int[] rints = new Random(47).ints(0, 1000)
            .limit(100).toArray();

    public static IntStream rands() {
        return Arrays.stream(rints);
    }
}
