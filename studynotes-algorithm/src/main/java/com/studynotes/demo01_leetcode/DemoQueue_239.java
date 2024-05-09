package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Description:
 * <p>
 * 239. 滑动窗口最大值
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。返回滑动窗口中的最大值。
 * <p>
 * 注意：时间复杂度必须为 O(n)。
 * <p>
 * 示例：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 */
public class DemoQueue_239 {

    @Test
    public void test() {
        int[] result = maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Description: 解题思路：
     * 1、维护一个滑动窗口队列，获取滑动窗口中的最大值
     */
    public int[] maxSlidingWindow0(int[] nums, int k) {
        List<Integer> result = new ArrayList<>(); // 存储滑动窗口中的最大值
        Queue<Integer> queue = new LinkedList<>(); // 滑动窗口，用于存储元素
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                queue.offer(nums[i]);
            } else {
                queue.offer(nums[i]); // 入队

                // 窗口满了，开始获取最大值

                int max = Integer.MIN_VALUE;
                for (Integer value : queue) {
                    max = Math.max(max, value); // 获取滑动窗口中的最大值
                }
                result.add(max);
                queue.poll(); // 出队
            }
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    /**
     * Description: 解题思路：
     * 1、维护一个滑动窗口队列
     * 2、由于 {@link this.maxSlidingWindow0} 的窗口每次移动都需要遍历窗口中的元素，获取最大值，时间复杂度为 O(n*k)。
     * 3、我们可以维护一个有序递减队列，新加入的元素与队列中的元素进行比较，如果新加入的元素大于队列中的元素，则将队列中的元素移除，
     * 直到队列为空或者新加入的元素小于队列中的元素。
     * 4、获取队列中的头部元素，即为滑动窗口中的最大值，时间复杂度为 O(n)。
     * 5、注意：需要记录每个元素的下标，如果头部元素已经在滑动窗口之外，需要将其移除。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>(); // 存储滑动窗口中的最大值
        LinkedList<Integer> queue = new LinkedList<>(); // 滑动窗口，用于存储元素
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                pushQueue(queue, nums[i], i);
            } else {
                pushQueue(queue, nums[i], i);
                result.add(queue.peek());
                // 如果头部元素已经在滑动窗口之外，需要将其移除
                if (headIndex.containsKey(queue.peek()) && i == headIndex.get(queue.peek()) + k - 1) {
                    queue.poll();
                }
            }
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    // 记录队列中元素的索引
    Map<Integer, Integer> headIndex = new HashMap<>();

    private void pushQueue(LinkedList<Integer> queue, Integer value, int index) {
        while (!queue.isEmpty() && queue.peekLast() < value) {
            queue.pollLast(); // 移除队列中比新加入的元素小的元素，保证队列中的元素是递减的
        }
        queue.offer(value);
        headIndex.put(value, index);
    }
}
