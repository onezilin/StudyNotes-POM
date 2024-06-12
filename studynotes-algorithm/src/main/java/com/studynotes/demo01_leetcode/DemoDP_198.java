package com.studynotes.demo01_leetcode;

/**
 * Description:
 * <p>
 * 198. 打家劫舍
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 实例1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class DemoDP_198 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、首先确定题目中的状态，创建 dp[] 一维数组，其中 i 表示当前小偷所在的房屋，dp[i] 表示当前最高金额
     * 3、确定状态转移方程：
     * dp[i] 表示当前在 i 房屋时的最高金额，最高金额为：
     * dp[i] = max([dp[0], dp[1], ... dp[i-2]) + nums[i]
     * 4、确定 base case，
     * 当 i 为 0 时，当前能偷窃的最大金额为 nums[0]
     * 当 i 为 1 时，当前能偷窃的最大金额为 max(nums[0], nums[1])
     * 偷倒数第一间房就不能偷倒数第二间，偷倒数第二间就不能偷倒数第一间，因此 maxMoney = max(dp[nums.length - 2], dp[nums.length - 1])
     */
    public static int rob(int[] nums) {
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
