package com.studynotes.demo01_leetcode;

/**
 * Description:
 */
public class Demo {

    /**
     * Description: 【前缀和技巧】适用于快速、频繁地计算一个索引【区间内的元素之和】，快速、频繁是指在 O(1) 时间复杂度内完成计算。
     * <p>
     * 1、前缀和的计算：对于数组 arr，计算前缀和数组 prefixSum，其中 prefixSum[i] = arr[0] + arr[1] + ... + arr[i - 1]。
     * 2、前缀和的应用：计算索引区间 [i, j] 内元素之和，只需计算 prefixSum[j+1] - prefixSum[i] 即可。
     * <p>
     * {@link DemoArray_303} 区域和检索 - 数组不可变
     * {@link DemoArray_304} 二维区域和检索 - 矩阵不可变
     * {@link DemoArray_560#subarraySum(int[], int)} 和为K的子数组
     */
    public void prefixSum() {
    }

    /**
     * Description: 【差分数组技巧】的主要适用场景是频繁地对原始数组的某个【区间的元素进行增减】。
     * <p>
     * 1、构建差分数组（diff[i] = nums[i] - nums[i - 1]）
     * 2、对于原始数组的区间 [i, j] 的元素进行增减时，只需要对差分数组的两个端点进行修改即可
     * 3、由于区间内元素都增加 val，只需对前端点 i 增加 val，对后端点 j + 1 减少 val
     * 4、最后根据差分数组构建原始数组，即 nums[i] = diff[i] + nums[i - 1]
     * <p>
     * {@link DemoArray_370#getModifiedArray(int, int[][])} 区间加法
     * {@link DemoArray_1109#corpFlightBookings(int[][], int)} 航班预订统计
     * {@link DemoArray_1094#carPooling(int[][], int)} 拼车
     */
    public void diffArray() {
    }
}
