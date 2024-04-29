package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Description:
 * <p>
 * 739. 每日温度
 * <p>
 * 根据每日气温列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度升高到超过该日的气温。如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例：
 * 输入：temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
 * 输出：[1, 1, 4, 2, 1, 1, 0, 0]
 */
public class DemoStack_739 {

    @Test
    public void test() {
        int[] result = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(result));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperature) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i; // 计算索引差值
            stack.push(i); // 存储索引
        }

        return result;
    }
}
