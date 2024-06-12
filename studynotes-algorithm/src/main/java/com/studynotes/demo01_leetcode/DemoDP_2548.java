package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 2548. 背包问题
 * <p>
 * 有一个背包，它的容量为 bag，现在有 n 个物品，第 i 个物品的重量为 weight[i]，价值为 value[i]。
 * 问可以装入背包的最大价值是多少？
 * <p>
 * 示例：
 * 输入：weight = [2, 1, 3], value = [4, 2, 3], bag = 3
 * 输出：6
 * 解释：装入第 0 个物品和第 1 个物品，总重量为 2 + 1 = 3，总价值为 4 + 2 = 6。
 */
public class DemoDP_2548 {

    @Test
    public void test() {
        int[] weight = {1, 2, 3};
        int[] value = {3, 4, 3};
        int bag = 3;
        System.out.println(maxPrice(weight, value, bag));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、创建二维数组 dp[][]，以背包大小为行，物品价值为列
     * 3、首先确定 base case，当背包大小为 0 时，当前背包能装的东西为 0
     * 4、再找出当前值和上下左右的关系，dp[i][j] 表示当背包大小为 i 时，第 j 个物品的放入/不放入时的最大价值：
     * 当有空间放入第 j 个物品时，去获取遍历到当前物品前能获取的最大价值，dp[i][j] = dp[i- weight[j]][j-1] + val；
     * 当没有空间放入第 j 个物品时，该背包价值不变，dp[i][j] = dp[i][j-1]。
     * 5、可以推导出公式：dp[i][j] = max(dp[i- weight[j]][j-1] + val, dp[i][j-1])
     */
    public int maxPrice(int[] weight, int[] value, int bag) {
        int[][] dp = new int[bag + 1][value.length];

        // 确定 base case
        // 当背包空间为 0 时，第一行值都是 0
        // 初始化第一列的值
        for (int i = 0; i < dp.length; i++) {
            if (i >= weight[0]) dp[i][0] = value[0];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int put = 0;
                if (i >= weight[j]) {
                    put = dp[i - weight[j]][j - 1] + value[j];
                }
                int noPut = dp[i][j - 1];
                dp[i][j] = Math.max(put, noPut);
            }
        }

        return dp[bag][value.length - 1];
    }
}
