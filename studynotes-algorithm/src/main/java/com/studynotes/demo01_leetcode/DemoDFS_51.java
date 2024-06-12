package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * <p>
 * 51. N 皇后
 * <p>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 * <p>
 * 注意：皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
 */
public class DemoDFS_51 {

    @Test
    public void test() {
        List<List<String>> lists = solveNQueens(5);
        System.out.println(lists);
    }

    /**
     * Description: 解题思路：
     * 1、使用回溯算法进行解题
     * 2、判断当前皇后位于当前位置是否合法，即判断左上、正上、右上的位置是否存在皇后，合法则添加到结果中
     * 3、递归，构建皇后
     */
    public List<List<String>> solveNQueens(int n) {
        List<char[]> list = new ArrayList<>();
        solveNQueens(list, n);
        return result;
    }

    List<List<String>> result = new ArrayList<>();

    public void solveNQueens(List<char[]> list, int n) {
        if (list.size() == n) {
            List<String> temp = list.stream().map(String::new).collect(Collectors.toList());
            result.add(temp);
            return;
        }

        for (int index = 0; index < n; index++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[index] = 'Q';
            if (!isValid(index, list)) continue; // 判断当前皇后位于当前位置是否合法
            list.add(chars);
            solveNQueens(list, n);
            list.remove(chars);
        }
    }

    // 用于判断左上、正上、右上的位置是否存在皇后
    public boolean isValid(int index, List<char[]> list) {
        if (list.size() == 0) return true;

        for (int i = list.size() - 1; i >= 0; i--) {
            char[] otherChars = list.get(i);
            if (otherChars[index] == 'Q') { // 正上
                return false;
            }
            int leftFlag = index - (list.size() - i); // 左上
            if (leftFlag >= 0 && otherChars[leftFlag] == 'Q') {
                return false;
            }
            int rightFlag = index + (list.size() - i); // 右上
            if (rightFlag < otherChars.length && otherChars[rightFlag] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
