package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 1094. 拼车
 * <p>
 * 假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车只能向一个方向行驶（即不允许掉头或改变方向，你可以将其想象为一个向量）。
 * 这儿有一份乘客行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location]，
 * 包含了第 i 组乘客的行程信息：num_passengers 是乘客的数量； start_location 是乘客的上车地点； end_location 是乘客的下车地点。
 * 你的任务是根据给定的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所有乘客的任务。如果可以，返回 true；否则，返回 false。
 * <p>
 * 示例 1：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4 输出：false
 * 示例 2：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5 输出：true
 * <p>
 * 提示：
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= trips[i][0] <= 100
 * 0 <= trips[i][1] < trips[i][2] <= 1000
 * 1 <= capacity <= 100000
 */
public class DemoArray_1094 {

    @Test
    public void test() {
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        System.out.println(carPooling(trips, 5));
    }

    /**
     * Description: 解题思路：
     * 1、使用差分数组技巧
     * 2、该题本质还是可以简化为差分数组，参考 {@link DemoArray_370#getModifiedArray(int, int[][])}
     * 3、这里注意一个细节是：乘客在 start_location 上车，在 end_location 下车，就表明 [start_location, end_location-1] 区间内，
     * 车上的人数是增加 num_passengers，因此是对 [start_location, end_location-1] 增加 num_passengers。
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001]; // 通过上述提示可知最多有 1001 个车站
        int[] diff = new int[1002];

        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }

        for (int[] trip : trips) {
            int val = trip[0];
            int i = trip[1];
            int j = trip[2] - 1; // 由于 j 站是下车点，因此 [i, j-1] 区间内，车上人是加 val 的
            diff[i] += val;
            diff[j + 1] -= val;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) nums[0] = diff[0];
            else nums[i] = diff[i] + nums[i - 1];
            if (nums[i] > capacity) return false; // 超载
        }

        return true;
    }
}
