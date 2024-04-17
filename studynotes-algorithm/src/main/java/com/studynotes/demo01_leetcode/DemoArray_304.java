package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 304. 二维区域和检索 - 矩阵不可变
 * <p>
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1)，右下角为 (row2, col2)。
 * 实现 NumMatrix 类：
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1)、右下角 (row2, col2) 的子矩阵的元素总和。
 * <p>
 * 示例 1：
 * 输入:
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[[3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]]],
 * [2, 1, 4, 3],
 * [1, 1, 2, 2],
 * [1, 2, 2, 4]]
 * 输出:
 * [null, 8, 11, 12]
 * 解释:
 * NumMatrix numMatrix = new NumMatrix(
 * [[3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 */
public class DemoArray_304 {

    @Test
    public void test() {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }

    /**
     * Description: 解题思路：
     * 1、构建前缀和数组 int[][] prefixSum
     * 2、prefixSum[i][j] 存储 matrix[0][0] ~ matrix[i-1][j-1] 的元素和
     * 3、计算 [row1, col1] 到 [row2, col2]，就是计算 prefixSum[row1, col1] 到 prefixSum[row2, col2] 的值
     */
    class NumMatrix {

        private int[][] prefixSum;

        public NumMatrix(int[][] matrix) {
            prefixSum = new int[matrix.length + 1][matrix[0].length + 1];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    prefixSum[i + 1][j + 1] =
                            prefixSum[i][j + 1] + prefixSum[i + 1][j] - prefixSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefixSum[row2 + 1][col2 + 1] - prefixSum[row2 + 1][col1] - prefixSum[row1][col2 + 1] + prefixSum[row1][col1];
        }
    }
}
