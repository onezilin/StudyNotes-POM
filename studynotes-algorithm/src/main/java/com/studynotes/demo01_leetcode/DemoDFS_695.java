package com.studynotes.demo01_leetcode;

/**
 * Description:
 * <p>
 * 695. 岛屿的最大面积
 * <p>
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或垂直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。找到给定的二维数组中最大的岛屿面积。（如果没有岛屿，则返回面积为 0 ）
 */
public class DemoDFS_695 {

    /**
     * Description: 解题思路：（参考 {@link DemoDFS_200#numIslands(char[][])}）
     * 1、和 DemoDFS_200 不同的是，还需要计算岛屿的大小，只需要对返回值进行累加即可
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[x].length || grid[x][y] == 0) return 0;
        grid[x][y] = 0;
        return 1 + dfs(grid, x - 1, y) + dfs(grid, x + 1, y) + dfs(grid, x, y - 1) + dfs(grid, x, y + 1);
    }
}
