package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Description:
 * <p>
 * 496. 下一个更大元素 I
 * <p>
 * 给你⼀个数组 nums，请你返回⼀个等长的结果数组，结果数组中对应索引存储着下⼀个更大元素，如果没有更⼤的元素，就存 -1
 * <p>
 * 示例：
 * 输入：[2,1,2,4,3]
 * 输出：[4,2,4,-1,-1]
 */
public class DemoStack_496 {

    @Test
    public void test() {
        int[] result = nextGreaterElement(new int[]{2, 1, 2, 4, 3});
        System.out.println(Arrays.toString(result));
    }

    /**
     * Description: 解题思路
     * 1、暴力解法，时间复杂度 O(n²)
     * 2、遍历数组，使用双重循环，找到当前元素的下一个更大元素
     */
    public int[] nextGreaterElement0(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = Integer.MIN_VALUE;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    result[i] = nums[j];
                    break;
                }
            }
            result[i] = result[i] == Integer.MIN_VALUE ? -1 : result[i];
        }
        return result;
    }

    /**
     * Description: 解题思路：上面时间复杂度 O(n²)，下面这种近似于 O(n)
     * 1、倒序遍历数组，使用栈存储元素；
     * 2、如果栈顶元素小于当前元素，则移除栈顶元素，直到栈为空或栈顶元素大于当前元素；
     * 3、如果栈为空，则说明后面没有更大的元素，返回 -1；否则返回栈顶元素。
     */
    public int[] nextGreaterElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(num);
        }
        return result;
    }
}
