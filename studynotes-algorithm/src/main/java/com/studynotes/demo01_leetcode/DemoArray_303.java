package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 303. 区域和检索 - 数组不可变
 * <p>
 * 给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * 实现 NumArray 类：
 * * NumArray(int[] nums) 使用数组 nums 初始化对象
 * * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，
 * 包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ..., nums[j])）
 * <p>
 * 示例：
 * 输入：["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[[-2, 0, 3, -5, 2, -1]]], [0, 2], [2, 5], [0, 5]]
 * 输出：[null, 1, -1, -3]
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
public class DemoArray_303 {

    @Test
    public void test() {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2)); // return 1 ((-2) + 0 + 3)
        System.out.println(numArray.sumRange(2, 5)); // return -1 (3 + (-5) + 2 + (-1))
        System.out.println(numArray.sumRange(0, 5)); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
    }

    /**
     * Description: 解题思路：
     * 1、构建前缀和数组 int[] prefixSum
     * 2、prefixSum[i] 用于存储 [0, i) 区间的元素之和
     * 3、计算 [i, j] 区间和就等于计算（preSum[j + 1] - preSum[i]）的差值
     */
    class NumArray {

        private int[] prefixSum;

        public NumArray(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = nums[i] + prefixSum[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }
}
