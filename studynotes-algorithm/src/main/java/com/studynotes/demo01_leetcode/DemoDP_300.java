package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 300. 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的某些元素而不改变其余元素的顺序形成。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。一个序列，如果每个元素比前一个元素大，那么这个序列就是递增序列。
 * <p>
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 */
public class DemoDP_300 {

    @Test
    public void test() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、构建 dp 数组：dp[i] 数组中记录记录当前 nums[0] 到 num[i] 的最长递增序列长度。
     * 3、可以发现规律：dp[i] 可以直接遍历 dp[0] 到 dp[i-1] 的最大递增序列，推导出公式：dp[i] = max(dp[i], dp[j] + 1)
     * 4、由于要组成递增子序列，再在步骤三的基础上添加一个条件：递增序列也就是 num[i] 大于前面递增序列的值
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // 初始化 base case，每个元素都可以作为一个子序列

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { // 当前元素大于递增序列最后一个值，才能进行下一步判断
                    dp[i] = Math.max(dp[j] + 1, dp[i]); // 获取该元素之前的最大递增序列长度
                }
            }
        }
        int maxSize = 0;
        for (int size : dp) {
            maxSize = Math.max(size, maxSize);
        }
        return maxSize;
    }
}
