package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 704. 二分查找
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，写一个函数搜索 nums 中的 target，
 * 如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 */
public class DemoArray_704 {

    @Test
    public void test() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println(search(nums, target));
    }

    /**
     * Description: 解题思路：
     * 1、使用二分搜索算法，每次取中间值，判断中间值是否等于目标值
     * 2、如果中间值大于目标值，则在左半区间继续查找；如果中间值小于目标值，则在右半区间继续查找；如果中间值等于目标值，则返回中间值的索引
     * 3、当左指针大于右指针时，说明没有找到目标值，返回 -1
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        // 搜索区间 [left, right]，当 left > right 时，搜索区间为空
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
