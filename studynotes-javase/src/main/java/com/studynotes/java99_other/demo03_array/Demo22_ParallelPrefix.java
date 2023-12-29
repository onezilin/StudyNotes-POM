package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// 使用 parallelPrefix() 可能相当复杂并且会改变原数组，
// 所以通常应该只在存在内存或速度问题(或两者都有)时使用。否则，Stream.reduce() 应该是您的首选。
public class Demo22_ParallelPrefix {
    static final int SIZE = 10_000_000;

    public static void main(String[] args) {
        long[] nums = new long[SIZE];
        Arrays.setAll(nums, n -> n);
        Arrays.parallelPrefix(nums, Long::sum);
        System.out.println("First 20: " + nums[19]);
        System.out.println("First 200: " + nums[199]);
        System.out.println("All: " + nums[nums.length - 1]);
    }
}
