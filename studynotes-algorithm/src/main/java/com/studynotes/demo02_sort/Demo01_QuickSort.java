package com.studynotes.demo02_sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * 912. 排序数组
 * <p>
 * 给你一个整数数组 nums，请你将该数组升序排列。
 */
public class Demo01_QuickSort {

    @Test
    public void test() {
        int[] nums = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        System.out.println("Before sort: " + Arrays.toString(nums));
        System.out.println("After sort: " + Arrays.toString(sortArray(nums)));
    }

    /**
     * Description: 解题思路
     * 1、从数组中选择一个元素作为基准值（pivot），通常选择第一个、最后一个或中间元素。
     * 2、将数组分为两部分
     * 3、从右往左找到第一个小于基准值的元素，从左往右找到一个大于基准值的元素，交换两个元素
     * 4、重复步骤3，直到两指针相遇
     * 5、将基准值与指针相遇的位置交换，保证基准值左边的数都小于等于基准值，右边的数都大于等于基准值
     * 6、继续对左右两部分进行排序
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int stone = nums[left];
        int head = left + 1;
        int tail = right;

        while (head < tail) {
            while (head < tail && nums[tail] > stone) tail--;
            while (head < tail && nums[head] < stone) head++;

            if (nums[head] > nums[tail]) {
                int temp = nums[head];
                nums[head] = nums[tail];
                nums[tail] = temp;
            }
        }
        if (stone > nums[tail]) {
            nums[left] = nums[tail];
            nums[tail] = stone;
        }

        quickSort(nums, left, tail - 1);
        quickSort(nums, head + 1, right);
    }
}
