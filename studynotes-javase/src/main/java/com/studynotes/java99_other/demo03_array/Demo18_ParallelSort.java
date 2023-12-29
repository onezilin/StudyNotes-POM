package com.studynotes.java99_other.demo03_array;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.IntStream;

// parallelSort() 并行排序，先将数组拆分成小数组，然后排序，再将结果合并
// 性能比sort好，可以使用parallel代替
public class Demo18_ParallelSort {
    public static void main(String[] args) {
        Instant ins1 = Instant.now();
        int[] array1 = IntStream.range(0, 100_000).toArray();
        Arrays.sort(array1);
        System.out.println(Duration.between(ins1, Instant.now()));

        Instant ins2 = Instant.now();
        int[] array2 = IntStream.range(0, 100_000).toArray();
        Arrays.parallelSort(array2);
        System.out.println(Duration.between(ins2, Instant.now()));
    }
}
