package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 188. 买卖股票的最佳时机 IV
 * <p>
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: k = 2, prices = [2,4,1]
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 */
public class DemoDP_188 {

    @Test
    public void test() {
        int[] prices = new int[]{1, 2, 3, 4, 5};
        System.out.println(maxProfit(2, prices));
    }

    /**
     * Description: 解题思路：参考 {@link DemoDP_123#maxProfit(int[])} 买卖股票的最佳时机 III
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0 || k == 0) return 0;
        int[][][] dp = new int[prices.length][k + 1][2];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[0]; // 第 0 天时，很容易推导出 dp[0][j][1] 都等于 -prices[i]
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }

        return dp[prices.length - 1][k][0];
    }
}
