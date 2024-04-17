package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 370. 区间加法
 * <p>
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k 个更新的操作。
 * 这些更新分别可以表示为：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
 * 请你返回 k 次操作后的数组。
 * <p>
 * 示例：
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 * 解释:
 * 初始状态: [0,0,0,0,0]
 * 进行了操作 [1,3,2] 后的状态: [0,2,2,2,0]
 * 进行了操作 [2,4,3] 后的状态: [0,2,5,5,3]
 * 进行了操作 [0,2,-2] 后的状态: [-2,0,3,5,3]
 */
public class DemoArray_370 {

    @Test
    public void test() {
        int length = 5;
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        int[] result = getModifiedArray(length, updates);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Description: 解题思路：
     * 1、构建差分数组（diff[i] = nums[i] - nums[i - 1]）
     * 2、对于原始数组的区间 [i, j] 的元素进行增减时，只需要对差分数组的两个端点进行修改即可
     * 3、由于区间内元素都增加 val，只需对前端点 i 增加 val，对后端点 j + 1 减少 val
     * 4、最后根据差分数组构建原始数组，即 nums[i] = diff[i] + nums[i - 1]
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        int[] diff = new int[length + 1];

        // 构建差分数组
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

        // 对差分数组进行操作
        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            diff[i] += val;
            diff[j + 1] -= val;
        }

        // 还原数组
        nums[0] = diff[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = diff[i] + nums[i - 1];
        }

        return nums;
    }
}
