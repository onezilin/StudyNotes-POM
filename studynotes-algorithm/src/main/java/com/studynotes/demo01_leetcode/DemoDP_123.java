package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: prices = [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天（股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 */
public class DemoDP_123 {

    @Test
    public void test() {
        int[] prices = new int[]{1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));
    }

    /**
     * Description: 解题思路：参考 {@link DemoDP_121#maxProfit(int[])}
     * 1、使用动态规划
     * 2、dp[i][k][0] = max(dp[i-1][k][1] + prices[i], dp[i-1][k][0])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i][k-1][0] - prices[i])
     * 3、和 {@link DemoDP_121#maxProfit(int[])} 不同的是，该题目中最多可以进行 2 次交易，
     * 因此需要对上面公式当 k = 0、1、2 时分别进行讨论
     * 当 k == 0 时，dp[i][0][0] = 0
     * 当 k == 1 时，dp[i][1][0] = max(dp[i-1][1][1] + prices[i], dp[i-1][1][0])；
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     * 当 k == 2 时，dp[i][2][0] = max(dp[i-1][2][1] + prices[i], dp[i-1][2][0])；
     * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][][] dp = new int[prices.length][3][2];

        dp[0][1][1] = -prices[0]; // 在第 0 天，持有股票
        dp[0][2][1] = -prices[0]; // dp[0][2][1] = max(dp[-1][2][1], dp[-1][1][0] - prices[i]) ≈ -prices[i]

        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][1] + prices[i], dp[i - 1][1][0]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][1] + prices[i], dp[i - 1][2][0]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }

        return dp[prices.length - 1][2][0];
    }
}
