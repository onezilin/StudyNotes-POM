package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 309. 最佳买卖股票时机含冷冻期
 * <p>
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）：
 * 1. 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 2. 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * <p>
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class DemoDP_309 {

    @Test
    public void test() {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
    }

    /**
     * Description: 解题思路：参考 {@link DemoDP_122#maxProfit(int[])}
     * 1、使用动态规划
     * 2、dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * // 由于有一天的冷却期，即未持有状态变成持有状态需要 2 天前，即 i - 2
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-2][k-1][0] - prices[i])
     * 3、该题目中可以进行多次交易，k 可以认为是无穷大，k ≈ k - 1，
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);

        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}
