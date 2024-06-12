package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 122. 买卖股票的最佳时机 II
 * <p>
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: prices = [7,1,5,3,6,8]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出，这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，这笔交易所能获得利润 = 6-3 = 3 。
 */
public class DemoDP_122 {

    @Test
    public void test() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit0(prices));
    }

    /**
     * Description: 解题思路：参考 {@link DemoDP_121#maxProfit(int[])}
     * 1、使用动态规划
     * 2、dp[i][k][0] = max(dp[i-1][k][1] + prices[i], dp[i-1][k][0])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * 3、和 {@link DemoDP_121#maxProfit(int[])} 不同的是，该题目中可以进行多次交易，k 可以认为是无穷大，k ≈ k - 1，
     * 因此上面的公式可以约等于下面：
     * dp[i][0] = max(dp[i-1][1] + prices[i], dp[i-1][0])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    /**
     * Description: 解题思路
     * 1、贪心算法，时间复杂度 O(n)
     * 2、遍历数组，如果当前元素大于前一个元素，则计算利润并累加
     */
    public int maxProfit0(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return profit;
    }
}
