package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 */
public class DemoArray_34 {

    @Test
    public void test() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(nums, target);
        System.out.println(result[0] + ", " + result[1]);
    }

    /**
     * Description: 解题思路：
     * 1、使用二分搜索算法
     * 2、需要在 O(logN) 时间复杂度内获取结果，因此需要使用二分搜索算法，参考 {@link DemoArray_704#search(int[], int)}
     * 3、该题在此基础上需要做一些改动，由于数组中存在重复数字，需要获取起始位置和结束位置。
     * 4、当 target == nums[mid] 时，为了获取起始位置，需要以 mid - 1 为 right，继续搜索左区间；
     * 为了获取结束位置，需要以 mid + 1 为 left，继续搜索右区间。
     */
    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);
        return new int[]{left, right};
    }

    public int binarySearch(int[] nums, int target, boolean flag) {
        int left = 0, right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                result = mid;
                // flag 为 ture 表示获取起始位置；为 false 表示获取结束位置
                if (flag) right = mid - 1;
                else left = mid + 1;
            }
        }

        return result;
    }
}
