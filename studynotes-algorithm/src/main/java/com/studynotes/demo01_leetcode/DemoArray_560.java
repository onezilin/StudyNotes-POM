package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 560. 和为K的子数组
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 */
public class DemoArray_560 {

    @Test
    public void test() {
        int[] nums = {1, 1, 1};
        System.out.println(subarraySum(nums, 2));
    }

    /**
     * Description: 解题思路：
     * 1、前缀和数组 preSum[i] 表示 [0, i) 区间的元素之和
     * 2、计算 [i, j] 区间和就等于计算（preSum[j + 1] - preSum[i]）的差值
     * 3、遍历数组，计算 preSum[j + 1] - preSum[i] == k 的个数
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (preSum[j] - preSum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
