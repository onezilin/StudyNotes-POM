package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 698. 划分为k个相等的子集
 * <p>
 * 给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 */
public class DemoDFS_698 {

    @Test
    public void test() {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) return false; // k 大于 nums 元素个数
        int sum = 0;
        for (int i : nums) sum += nums[i];
        if (sum % k != 0) return false; // 不能平分

        int[] bucket = new int[k]; // k 个桶，记录每个桶中元素和
        int target = sum / k; // 预计每个桶的元素和
        return backtrack(nums, 0, bucket, target);
    }

    public boolean backtrack(int[] nums, int index, int[] bucket, int target) {
        if (index == nums.length) {
            // 检查每个桶中元素和是否为 target
            for (int bucketSum : bucket) {
                if (bucketSum != target) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] + nums[index] > target) continue; // 装入后大于预期值，则跳过
            bucket[i] += nums[index]; // 选择
            if (backtrack(nums, index + 1, bucket, target)) {
                return true; // 如果元素分完且和相同，则返回
            }
            bucket[i] -= nums[index]; // 撤销
        }
        // nums[index] 装入哪个桶都不行
        return false;
    }
}
