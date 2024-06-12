package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * <p>
 * 773. 滑动谜题
 * <p>
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一个空方块用 0 来表示.
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换. 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个最终板状态 board，返回最少进行多少次移动能解开谜板。如果不能解开谜板，返回 -1.
 * <p>
 * 示例：
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5，1 步完成
 */
public class DemoBFS_773 {

    @Test
    public void test() {
        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        System.out.println(slidingPuzzle(board));
    }

    /**
     * Description: 解题思路：
     * 1、这种有多种步骤，但最后需要返回一个最小步骤的题目，需要联想到 BFS 算法。
     * 2、每步相当于上下左右滑动 0 这个滑块，与上下左右的值相交换，最终返回需要的结果
     * 3、在这个题目中最终返回的二维数组从左到右，从上倒下是有序的，结果就是个有序一维数组，可以将初始数组转化为一维数组进行操作。
     * 4、难点在于想到记录当前二维数组中的元素，上下左右在一维数组中对应的位置
     */
    public int slidingPuzzle(int[][] board) {
        String boardString = translate(board);
        int step = 0;
        HashSet<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.add(boardString);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                boardString = queue.poll();
                if (expect.equals(boardString)) return step;
                // 获取 0 的位置，即滑块的位置
                int zeroIndex = 0;
                String[] array = boardString.split(",");
                for (int index = 0; index < array.length; index++) {
                    if ("0".equals(array[index])) {
                        zeroIndex = index;
                        break;
                    }
                }
                // 获取 0 上下左右的位置
                List<Integer> neighbor = neighborList.get(zeroIndex);
                // 上下左右进行滑动后，将结果入队
                for (int neighborIndex : neighbor) {
                    String[] tempArr = boardString.split(",");
                    String tempStr = tempArr[zeroIndex];
                    tempArr[zeroIndex] = tempArr[neighborIndex];
                    tempArr[neighborIndex] = tempStr;
                    String nextStr = String.join(",", tempArr);
                    if (!visited.contains(nextStr)) {
                        queue.offer(nextStr);
                        visited.add(nextStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String expect; // 预期的结果
    private final List<List<Integer>> neighborList = new ArrayList<>(); // 二维数组中周围坐标

    public String translate(int[][] board) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                list.add(board[i][j]);
                int index = list.size() - 1; // 在一维数组中的下标
                // 记录当前一维数组下标在二维数组中上下左右的位置，方便之后的滑块移动
                List<Integer> temp = new ArrayList<>();
                if (i > 0) {
                    int up = index - board[i - 1].length;
                    temp.add(up);
                }
                if (i < board.length - 1) {
                    int down = index + board[i].length;
                    temp.add(down);
                }
                if (j > 0) {
                    int left = index - 1;
                    temp.add(left);
                }
                if (j < board[i].length - 1) {
                    int right = index + 1;
                    temp.add(right);
                }
                neighborList.add(temp);
            }
        }

        // 记录初始状态
        String string = list.stream().map(Object::toString).collect(Collectors.joining(","));

        // 记录预期状态，即 [1、2、3、4、5、0]
        list.sort((Comparator.comparingInt(o -> o)));
        Integer zero = list.remove(0);
        list.addLast(zero);
        expect = list.stream().map(Object::toString).collect(Collectors.joining(","));

        return string;
    }
}
