package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 931. 下降路径最小和
 * <p>
 * 给定一个 n x n 方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 * 下降路径可以从第一行的任何元素开始，需要下降到最后一行，每次下降可以向下、向右下、向左下三个方向移动一格。
 * <p>
 * 示例：
 * 输入：[[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：
 * 下降路径0：1 + 5 + 7 = 13
 * 下降路径1：1 + 4 + 8 = 13
 */
public class DemoDP_931 {

    @Test
    public void test() {
        int[][] arr = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(minFallingPathSum(arr));
    }

    /**
     * Description: 解题思路：
     * 1、使用动态规划
     * 2、当前位置最小值为，左上、上、右上中的最小值相加的结果
     * 3、可以推导出公式：F(i,j) = min(F(i-i,j-1), F(i-1, j), F(i-1, j+1)) + matrix(i, j)
     * 4、获取最后一行就是每条路径的最小值，获取最后一行的最小值就是所有路径的最小值。
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] minRouteSum = new int[n][n];
        for (int i = 0; i < n; i++) {
            minRouteSum[0][i] = matrix[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int leftTop = j == 0 ? Integer.MAX_VALUE : minRouteSum[i - 1][j - 1];
                int top = minRouteSum[i - 1][j];
                int rightTop = j == n - 1 ? Integer.MAX_VALUE : minRouteSum[i - 1][j + 1];
                minRouteSum[i][j] = Math.min(Math.min(leftTop, top), rightTop) + matrix[i][j];
            }
        }
        int minRoute = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minRoute = Math.min(minRoute, minRouteSum[n - 1][i]);
        }
        return minRoute;
    }
}
