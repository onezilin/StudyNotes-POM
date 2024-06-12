package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * Description:
 * <p>
 * 694. 不同的岛屿数量
 * <p>
 * 给定一个非空 2D 数组 grid ，由 0 和 1 组成，一个岛屿是由四个方向 (水平或垂直) 的 1 （代表土地） 构成的组合。
 * 当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合，两个岛屿被认为是相同的。
 */
public class DemoDFS_694 {

    @Test
    public void test() {
        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 1, 1}
        };
        System.out.println(numDistinctIslands(grid));
    }

    private final HashSet<String> set = new HashSet<>();

    /**
     * Description: 解题思路：
     * 1、要查看每个岛屿是否一样，就是查看岛屿每块陆地的位置，因此在遍历岛屿时，记下走的方向和步骤，使用字符串存储最终路径，使用 HashSet 去重
     * 2、只要相同的岛屿按照相同的方式遍历，结果就是相同的
     */
    public int numDistinctIslands(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 如果当前位置是1，就开始深度优先搜索
                if (grid[i][j] == 1) {
                    set.add(dfs(grid, i, j));
                }
            }
        }
        return set.size();
    }

    public String dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[x].length || grid[x][y] == 0) return "0";
        grid[x][y] = 0;
        // 使用 1、2、3、4 表示左右上下方向的结果
        String left = "1" + dfs(grid, x - 1, y);
        String right = "2" + dfs(grid, x + 1, y);
        String up = "3" + dfs(grid, x, y - 1);
        String down = "4" + dfs(grid, x, y + 1);
        return left + right + up + down;
    }
}
