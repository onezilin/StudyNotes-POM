package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

/**
 * Description:
 * <p>
 * 200. 岛屿数量
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格 grid，请你计算网格中岛屿的数量。
 * <p>
 * 注意：岛屿总是被水包围，并且每座岛屿只能由上下左右的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class DemoDFS_200 {

    @Test
    public void test() {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }

    /**
     * Description: 解题思路：
     * 1、传统方式遍历数组是使用 for 循环；也可以使用 dfs 使用递归的方式 {@link this#dfs(char[][], int, int)} 遍历数组
     * 2、以当前 '1' （陆地）为起点，蔓延地查看上下左右是否为陆地；如果为陆地则进行下一次递归
     * 3、查询后将 '1' 置为 '0'，表示已经搜索过改陆地
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++; // 遍历完此岛屿的所有陆地后自增
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[x].length || grid[x][y] == '0') return;
        grid[x][y] = '0'; // 置为 '0' 表示已经搜索过
        dfs(grid, x - 1, y); // 左
        dfs(grid, x + 1, y); // 右
        dfs(grid, x, y - 1); // 上
        dfs(grid, x, y + 1); // 下
    }
}
