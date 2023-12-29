package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;
import java.util.stream.IntStream;

// parallelPrefix() 会对前一个元素和当前元素进行操作，返回的结果替代当前位置的元素
public class Demo21_ParallelPrefix {
    public static void main(String[] args) {
        int[] a = IntStream.range(0, 10).toArray();
        System.out.println("a" + Arrays.toString(a));
        // reduce 会将前一个元素和当前元素操作,并返回一个值继续传给下一步操作
        System.out.println(Arrays.stream(a).reduce(Integer::sum).getAsInt());
        Arrays.parallelPrefix(a, Integer::sum);
        System.out.println("a after prefix" + Arrays.toString(a));

        String[] b = IntStream.range(0, 10).boxed().map(i -> i + "").toArray(String[]::new);
        System.out.println("b" + Arrays.toString(b));
        Arrays.parallelPrefix(b, (pre, behind) -> pre + behind);
        System.out.println("b after prefix" + Arrays.toString(b));
    }
}
