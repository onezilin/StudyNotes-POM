package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 26. 删除有序数组中的重复项
 * <p>
 * 给你一个有序数组 nums，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
 * 删除后元素的相对顺序应该保持一致，然后返回 nums 中唯一元素的个数。
 * <p>
 * 注意：不要使用额外的数组空间，你必须在原地修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 */
public class DemoArray_26 {

    @Test
    public void test() {
        int[] nums = {0, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5};
        System.out.println(removeDuplicates(nums));
    }

    /**
     * Description: 解题思路：
     * 1、使用快慢指针
     * 2、我们可以把这个队伍想象成重新排队，慢指针指向排队后的队尾，快指针遍历队伍，快指针发现元素和队尾一致则跳过；
     * 发现和排队后的队尾不一致，则将该元素放在排队后的队尾的后一位
     * <p>
     * 为什么不先放到队尾再加 1？
     * 因为队尾 slow 需要先与 fast 比较是否不一致，不一致才放到队伍中，slow 应该指向的是已经排队的最后一位，而不是新的队尾（即队尾后一位的位置）
     * <p>
     * 3、最后返回慢指针的位置 + 1，即为新数组的长度
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++; // 队尾后面一位
                nums[slow] = nums[fast];
            }
            fast++;
        }

        return slow + 1; // 下标位加 1 才是长度
    }
}
