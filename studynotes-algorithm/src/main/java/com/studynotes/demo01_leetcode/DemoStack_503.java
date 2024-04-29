package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Description:
 * <p>
 * 503. 下一个更大元素 II
 * <p>
 * 给你⼀个循环数组 nums，请你输出每个元素的下一个更大元素，输出应该以数组形式返回，其中下一个更大元素一定存在。如果不存在，返回 -1。
 * <p>
 * 示例：
 * 输入：[1,2,1]
 * 输出：[2,-1,2]
 */
public class DemoStack_503 {

    @Test
    public void test() {
        int[] result = nextGreaterElements(new int[]{1, 2, 1});
        System.out.println(Arrays.toString(result));
    }

    /**
     * Description: 解题思路：
     * 1、由于本题中是环形数组，因此拼接两个数组，即可将环形数组转换为普通数组；
     * 2、倒序遍历数组，使用栈存储元素；
     * 3、使用 i % nums.length 可以获取真实索引；
     */
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        for (int i = nums.length * 2 - 1; i >= 0; i--) {
            int num = nums[i % nums.length];
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }
            result[i % nums.length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(num);
        }
        return result;
    }
}
