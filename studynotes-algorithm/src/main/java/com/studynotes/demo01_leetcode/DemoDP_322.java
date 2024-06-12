package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description:
 * <p>
 * 322. 零钱兑换
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 */
public class DemoDP_322 {

    @Test
    public void test() {
        int[] coins = {1, 2, 5};
        int amount = 100;
        System.out.println(coinChange(coins, amount));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、创建 dp 数组，以下标表示当前金额 dp[i] 表示当前金额的最小组成数量
     * 3、只需要遍历 dp[i - coin[j]] 获取最小硬币数后加 1，就是当前金额所需的最小数量
     * 4、可以推导出公式：Fn = min(Fn - coins[0], Fn - coins[1], Fn - coins[2], ..., Fn - coins[n]) + 1
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0; // 设置初始值

        for (int i = 1; i < dp.length; i++) {
            // -1 是如果没有任何一种硬币组合能组成总金额时，后面的 minCount + 1 不会溢出
            int minCount = Integer.MAX_VALUE - 1;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    minCount = Math.min(minCount, dp[i - coin]); // 获取最小的组成数量
                }
            }
            dp[i] = minCount + 1;
        }

        // 值为 Integer.MAX_VALUE 表示不能组成，返回 -1
        return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }

    /**
     * Description: 解题思路：
     * 1、使用 BFS 算法，从 amount 开始，每次减去一个 coin，直到减到 0，每次 Queue 中存储的是剩余的金额
     * 2、时间复杂度为 O(k^n)，k 为 coins 的长度，n 为 amount 的大小
     */
    public int coinChange0(int[] coins, int amount) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(amount);
        List<Integer> visited = new LinkedList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int remainAmount = queue.poll();
                if (remainAmount == 0) return count;
                for (int coin : coins) {
                    int curAmount = remainAmount - coin;
                    if (curAmount >= 0 && !visited.contains(curAmount)) {
                        queue.offer(curAmount);
                        visited.add(curAmount);
                    }
                }
            }
            count++;
        }

        return -1;
    }
}
