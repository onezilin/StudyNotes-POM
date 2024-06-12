package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 1905. 统计子岛屿
 * <p>
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0（表示水域）和 1（表示陆地）。一个 岛屿 是由四个方向 (水平或垂直) 上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 * 如果 grid2 的一个岛屿完全由 grid1 的一个岛屿组成，则称 grid2 中的这个岛屿为子岛屿 。
 * 请你返回 grid2 中 子岛屿 的数目 。
 */
public class DemoDFS_1905 {

    @Test
    public void test() {
        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
        };
        int[][] grid2 = {
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
        };
        System.out.println(countSubIslands(grid1, grid2));
    }

    /**
     * Description: 解题思路：（参考 {@link DemoDFS_200#numIslands(char[][])}）
     * 1、grid2 中的子岛的所有陆地必须在 grid1 中也是陆地，否则就不是子岛了
     * 2、因此先消除 grid2 中是陆地而 grid1 中是水域的岛，剩下的就是子岛了
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                // 如果 grid2 中是陆地而 grid1 中是水域，则说明该陆地所在的岛不是子岛，先消除该岛
                if (grid1[i][j] != grid2[i][j]) {
                    dfs(grid2, i, j);
                }
            }
        }

        // 消除后，剩下的岛屿就是子岛
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                if (grid2[i][j] == 1) {
                    dfs(grid2, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[x].length || grid[x][y] == 0) return;
        grid[x][y] = 0;
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }
}
