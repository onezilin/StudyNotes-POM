package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 1109. 航班预订统计
 * <p>
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * 我们这儿有一份航班预订表，表中第 i 条预订记录 bookings[i] = [i, j, k] 意味着我们在从 i 到 j 的每个航班上预订了 k 个座位。
 * 请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。
 * <p>
 * 示例：
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号     1   2   3   4   5
 * 预订记录 1： 10  10
 * 预订记录 2：     20  20
 * 预订记录 3：     25  25  25  25
 * 总座位数：   10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 */
public class DemoArray_1109 {

    @Test
    public void test() {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        int[] result = corpFlightBookings(bookings, n);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Description: 解题思路
     * 1、使用差分数组技巧
     * 2、该题本质还是可以简化为差分数组，参考 {@link DemoArray_370#getModifiedArray(int, int[][])}
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        int[] diff = new int[n + 1];

        // 构建差分数组
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

        // 对差分数组进行操作
        for (int[] booking : bookings) {
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            diff[i] += val;
            diff[j + 1] -= val;
        }

        // 根据差分数组构建原始数组
        nums[0] = diff[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = diff[i] + nums[i - 1];
        }

        return nums;
    }
}
