package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 875. 爱吃香蕉的珂珂
 * <p>
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时），每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
 * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来之前吃掉所有的香蕉，返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 * <p>
 * 示例 1：
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * <p>
 * 提示：
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 */
public class DemoArray_875 {

    @Test
    public void test() {
        int[] piles = {805306368, 805306368, 805306368};
        int H = 1000000000;
        System.out.println(minEatingSpeed(piles, H));
    }

    /**
     * Description: 解题思路：
     * 1、使用二分搜索算法
     * 2、设吃香蕉速度为 x，吃完 piles[] 香蕉需要，y = f(piles, x)。吃得越快，用时越短，f(piles, x) 在二维坐标中生成的折线图如下：
     * <p>
     * y
     * |
     * |\
     * | \
     * |  \
     * |   ——————
     * |             \
     * |              \
     * |               \
     * --------------------> x
     * <p>
     * 3、我们需要找到 y 轴的值为 H 的 x 值，即 f(piles, x) = H，这个 x 值就是我们要求的最小速度 K。
     * 4、可以把这条折线看成一个单调递减的数组：
     * y 轴时间数组为 hours[] = {27, 15, 10, 8, 8, 6, 5, 5, 5, 5}，
     * x 轴速度数组为 speed[] = { 1,  2,  3, 4, 5, 6, 7, 8, 9, 10}
     * 5、我们需要找到 hours[] 中第一个等于 H 的值，对应的 speed[] 就是我们要求的最小速度 K。
     * 可以使用二分搜索算法，提高查找速度，参考 {@link DemoArray_34#searchRange(int[], int)}。
     */
    public int minEatingSpeed(int[] piles, int h) {
        // 题目中一堆香蕉取值范围为 1 <= piles[i] <= 10^9，最大一次只能吃一堆，所以最大值为 10^9
        int left = 1, right = 1000000000;

        while (left <= right) {
            int mid = (left + right) / 2;
            long hours = f(piles, mid);
            if (hours < h) {
                right = mid - 1; // 吃得太快了，去左区间查找
            } else if (hours > h) {
                left = mid + 1; // 吃得太慢了，去右区间查找
            } else {
                right = mid - 1; // 由于需要找最小速度，因此去左区间查找
            }
        }

        // 应该在保证 hours 必须小于等于 h 前提下，获取最小的速度
        return left;
    }

    // 注意：这里返回值设为 long 类型，防止溢出
    public long f(int[] piles, int x) {
        long hours = 0;
        for (int pile : piles) {
            hours += pile / x;
            // 如果还剩一点没吃完，需要在下一个小时继续吃完
            if (pile % x > 0) {
                hours++;
            }
        }
        return hours;
    }
}
