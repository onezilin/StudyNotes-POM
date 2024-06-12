package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 213. 打家劫舍 II
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这次你要偷窃的房屋呈环状排列，这意味着第一间房屋和最后一间房屋是相邻的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算你在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 * 实例1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）。
 */
public class DemoDP_213 {

    @Test
    public void test() {
        int[] nums = {2, 3, 2};
        System.out.println(rob(nums));
    }

    /**
     * Description: 解题思路：参考 {@link DemoDP_198#rob(int[])}
     * 1、使用动态规划
     * 2、由于首尾房屋不能同时偷，因此分两种情况讨论：
     * 3、第一种情况：偷第一间房屋，不偷最后一间房屋
     * 4、第二种情况：不偷第一间房屋，偷最后一间房屋
     * 5、两种情况取最大值
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return Math.max(robRange(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                robRange(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    public int robRange(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            for (int j = 0; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
            }
        }

        return Math.max(dp[nums.length - 2], dp[nums.length - 1]);
    }
}
