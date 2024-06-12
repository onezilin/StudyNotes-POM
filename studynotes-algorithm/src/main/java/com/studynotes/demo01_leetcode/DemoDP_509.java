package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 509. 斐波那契数
 * <p>
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 * 也就是：
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 */
public class DemoDP_509 {

    @Test
    public void test() {
        System.out.println(fib(4));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、从最底下、最简单、规模最小的 f(1) 和 f(2) 开始往上推，直到获取到结果
     * 3、可以推导出公式：F(n) = F(n - 1) + F(n - 2)
     */
    public int fib(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2]; // 使用动态规划，自底向上
        }
        return arr[arr.length - 1];
    }

    /**
     * Description: 解题思路：
     * 1、推导出状态转移方程，直接使用递归的方式
     */
    public int fib0(int n) {
        if (n == 0 || n == 1) return 1;
        return fib(n - 1) + fib(n - 2); // 使用递归方式，自顶向下，逐步分解问题
    }
}
