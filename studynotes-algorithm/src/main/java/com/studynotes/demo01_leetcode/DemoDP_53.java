package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 * <p>
 * 示例：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
 */
public class DemoDP_53 {

    @Test
    public void test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、创建 dp 数组，当前 dp[i] 数组记录 nums[0] 到 nums[i] 组成的最大和
     * 3、由于数组中存在负值，因此 dp[i] 有两种选择：与前面值相加生成一个更大的和；不与前面子数组连接，自己作为一个子数组
     * 4、推导出公式：dp[i] = max(dp[i - 1] + nums[i], num[i])
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}
