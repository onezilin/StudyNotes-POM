package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 167. 两数之和 II - 输入有序数组
 * <p>
 * 给定一个已按照升序排列的整数数组 numbers，请你从数组中找出两个数满足相加之和等于目标数 target。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * 你可以假设每个输入只对应唯一的答案 ，而且你不可以重复使用相同的元素。
 * <p>
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此，索引为 1 和 2 的元素之和等于 9
 */
public class DemoArray_167 {

    @Test
    public void test() {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Description: 解题思路：
     * 1、由于这是一个有序数组，所以可以使用左右双指针的方式来解决问题
     * 2、定义两个指针 left 和 right，分别指向数组的第一个元素和最后一个元素
     * 3、如果 numbers[left] + numbers[right] == target，则返回 left 和 right 的下标
     * 4、如果 numbers[left] + numbers[right] < target，则说明小了，左边 left++
     * 5、如果 numbers[left] + numbers[right] > target，则说明大了，右边 right--
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++; // 小了，左边指针右移
            } else {
                right--; // 大了，右边指针左移
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Description: 解题思路：
     * 1、使用传统的 for 循环遍历数组，时间复杂度为 O(n^2)
     */
    public int[] twoSum0(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
