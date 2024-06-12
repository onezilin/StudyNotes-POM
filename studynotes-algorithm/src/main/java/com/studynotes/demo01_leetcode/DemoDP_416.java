package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 416. 分割等和子集
 * <p>
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
 * <p>
 * 注意：
 * 1、每个数组中的元素不会超过 100
 * 2、数组的大小不会超过 200
 * <p>
 * 示例 1：
 * 输入：[1, 5, 11, 5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11]
 */
public class DemoDP_416 {

    @Test
    public void test() {
        int[] nums = {1, 3, 5, 5, 5, 5};
        System.out.println(canPartition(nums));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、首先计算数组的总和 sum，如果 sum 是奇数，那么肯定不能分割成两个和相等的子集
     * 3、如果 sum 是偶数，那么问题就转化成了一个背包问题：从数组中挑选一些数，该数组重量为 nums[]，值为 nums[]，使得这些数的总价值等于 sum/2
     * 4、先确定 base case，以背包容量为行，nums[] 数组为列，第一行容量为 0 是价值都是 0；第一列 i > nums[0] 可以直接放入背包
     * 当有空间放入第 j 个物品时，去获取遍历到当前物品前能获取的最大价值，dp[i][j] = dp[i- weight[j]][j-1] + val；
     * 当没有空间放入第 j 个物品时，该背包价值不变，dp[i][j] = dp[i][j-1]。
     * 5、可以推导出公式：dp[i][j] = max(dp[i- weight[j]][j-1] + val, dp[i][j-1])
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        sum = sum / 2;

        int[][] dp = new int[sum + 1][nums.length];
        // 初始化第一列
        for (int i = 0; i < dp.length; i++) {
            if (i >= nums[0]) dp[i][0] = nums[0];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int put = 0;
                if (i >= nums[j]) {
                    put = dp[i - nums[j]][j - 1] + nums[j];
                }
                int notPut = dp[i][j - 1];
                dp[i][j] = Math.max(put, notPut);
            }
        }

        return dp[sum][nums.length - 1] == sum;
    }
}
