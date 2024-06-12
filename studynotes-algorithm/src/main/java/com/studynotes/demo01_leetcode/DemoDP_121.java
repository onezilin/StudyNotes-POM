package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 121. 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润，返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0。
 * <p>
 * 注意：最多只能买入一次
 * <p>
 * 示例：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意：你不能在第 1 天和第 2 天接连购买股票，之后再将它卖出。 因为这样属于同时参与多笔交易，你必须在再次购买前出售掉之前的股票。
 */
public class DemoDP_121 {

    @Test
    public void test() {
        int[] prices = {7, 1, 5, 3, 6, 1, 4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit0(prices));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、先确定题目中的状态，创建 dp[][][] 三维数组，用于存储当前最大利润，第一个是表示天数，第二个是剩余买入次数，第三个是当前状态：
     * 2.1) 天数：当前是第几天
     * 2.2) 买入次数：当前已经买入几次，值得注意的是，买入股票后必须全部卖出，才能进行下一次买入
     * 2.3) 当前状态：持有股票 1、未持有股票 0，持有股票可以选择继续持有，也可以选择卖出，未持有股票可以选择继续未持有，也可以选择买入。
     * 3、确定状态转移方程：
     * 3.1) dp[i][k][0]，表示当前第 i 天、买入 k 次、未持有股票：那么前一天持有股票，今天卖了；前一天没有持有股票，今天继续未持有。
     * 最大利润为 dp[i][k][0] = max(dp[i-1][k][1] + prices[i], dp[i-1][k][0])
     * 3.2) dp[i][k][1]，表示当前第 i 天，买入 k 次、持有股票：那么前一天持有股票，今天继续持有；前一天没有持有股票，今天买了，k - 1 变成 k。
     * 最大利润为 dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * 4、确定 base case：
     * 当 k 为 0 时，不能买卖，因此 dp[i][0][0] = 0
     * 当 i 为 0 时，如果持有股票 dp[0][k][1] = -prices[0]，如果不持有股票 dp[0][k][0] = 0；当 i < 0 时，可以认为 dp[i] 足以忽略，方便取最大值
     * 5、在本题中，最多只能进行一次买入操作，k 为 1，
     * k == 0 时，dp[i][0][0] = 0
     * k == 1 时，dp[i][1][0] = max(dp[i-1][1][1] + price[i], dp[i-1][1][0])，
     * dp[i][1][1] = max(dp[i-1][1][1], 0 - prices[i])
     * 由上述公式可以看出将 k 省略对公式没有影响：dp[i][k][s] ≈ dp[i][s]
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];

        dp[0][1] = -prices[0]; // 第 0 天持有股票，利润为 -prices[0]
        dp[0][0] = 0; // 第 0 天未持有股票，利润为 0

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[prices.length - 1][0]; // 返回未持有状态的结果肯定收益更大
    }

    /**
     * Description: 解题思路：
     * 1、由于要获取最大的利润，因此卖出的时候必须要比买入的时候价格高，因此从后往前遍历。
     * 2、当前面的价格比当天价格低时，计算差价；当前面的价格比当天价格高时，记录该价格。
     * 3、使用 maxProfit 记录最大差价，使用 maxPrice 记录最高价格
     */
    public int maxProfit0(int[] prices) {
        if (prices.length == 0) return 0;
        int maxPrice = prices[prices.length - 1]; // 最大价格
        int maxProfit = 0; // 最大差价
        for (int i = prices.length - 2; i >= 0; i--) {
            if (maxPrice > prices[i]) {
                maxProfit = Math.max(maxProfit, maxPrice - prices[i]);
            } else {
                maxPrice = prices[i];
            }
        }
        return maxProfit;
    }
}
