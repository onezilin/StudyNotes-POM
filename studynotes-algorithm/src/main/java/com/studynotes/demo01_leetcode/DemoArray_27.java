package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 27. 移除元素
 * <p>
 * 给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 注意：
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以更改。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2，并且 nums 中的前两个元素均为 2。不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums 数组的前两个元素为 2 和 2 。
 * 例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 */
public class DemoArray_27 {

    @Test
    public void test() {
        int[] nums = {3, 2, 2, 3};
        System.out.println(removeElement(nums, 3));
    }

    /**
     * Description: 解题思路：
     * 1、使用快慢指针
     * 2、我们可以把这个队伍想象成重新排队，慢指针指向排队后的队尾，快指针遍历队伍，快指针发现元素等于 val，则跳过；
     * 不等于 val，则加入队尾
     * 3、最后返回慢指针的位置，即为新数组的长度
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }
}
