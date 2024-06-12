package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 714. 买卖股票的最佳时机含手续费
 * <p>
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 */
public class DemoDP_714 {

    @Test
    public void test() {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(prices, 2));
    }

    /**
     * Description: 解题思路：参考 {@link DemoDP_122#maxProfit(int[])}
     * 1、使用动态规划
     * 2、由于卖出时需要计算手续费，因此 -fee
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i] - fee)
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * 3、该题目中可以进行多次交易，k 可以认为是无穷大，k ≈ k - 1，
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i] - fee)
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices.length < 2) return 0;
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}
