package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 283. 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class DemoArray_283 {

    @Test
    public void test() {
        int[] nums = {2, 1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Description: 解题思路：
     * 1、使用快慢指针
     * 2、我们可以把这个队伍想象成重新排队，慢指针指向排队后的队尾，快指针遍历队伍，0 可以看成空位，快指针发现元素为 0 则跳过；
     * 不为 0，则交换位置，即把非 0 元素放在队尾
     */
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }
    }
}
