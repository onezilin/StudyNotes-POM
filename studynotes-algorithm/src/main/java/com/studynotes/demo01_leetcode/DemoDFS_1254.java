package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 1254. 统计封闭岛屿的数目
 * <p>
 * 有一个二维矩阵 grid ，每个位置要么是陆地（'0'）要么是水域（'1'）。
 * 我们从一块陆地出发，每次可以往上、下、左、右四个方向上行走，能走到的所有陆地被称作岛屿。
 * 如果一片陆地周围被水域包围（即被水域包围的陆地是无法从岛屿上的陆地走到的），那么我们认为这一片陆地是封闭岛屿。请返回封闭岛屿的数目。
 * <p>
 * 注意：岛屿总是被水包围，并且每座岛屿只能由上下左右的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被陆地包围。
 */
public class DemoDFS_1254 {

    @Test
    public void test() {
        int[][] grid = {
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
        };
        System.out.println(closedIsland(grid));
    }

    /**
     * Description: 解题思路：（参考 {@link DemoDFS_200#numIslands(char[][])}）
     * 1、和 DemoDFS_200 不同的是，此题是统计封闭岛屿的数量，因此需要先排除和四周相连的岛屿，再进行统计
     */
    public int closedIsland(int[][] grid) {
        int count = 0;

        // 排除和四周相连的岛屿
        for (int i = 0; i < grid.length; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, grid[i].length - 1);
        }
        for (int j = 0; j < grid[0].length; j++) {
            dfs(grid, 0, j);
        }
        for (int j = 0; j < grid[grid.length - 1].length; j++) {
            dfs(grid, grid.length - 1, j);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[x].length || grid[x][y] == 1) return;
        grid[x][y] = 1; // 置为 1 表示已经搜索过
        dfs(grid, x - 1, y); // 左
        dfs(grid, x + 1, y); // 右
        dfs(grid, x, y - 1); // 上
        dfs(grid, x, y + 1); // 下
    }
}
