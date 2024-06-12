package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 * <p>
 * 752. 打开转盘锁
 * <p>
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把'9'变为'0'，'0'变为'9'。每次旋转都只能旋转一个拨轮的一位数字。锁的初始数字为'0000' ，一个代表四个拨轮的数字的字符串。
 * 列表deadends包含了一组死亡数字，一旦拨轮的数字和deadends中的任何一个相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回-1。
 * <p>
 * 示例 1:
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202" 输出：6
 * 解释： 一个可能的解锁路径是： 0000 -> 1000 -> 1100 -> 1200 -> 1201 -> 1202 -> 0202。
 * 示例 2:
 * 输入: deadends = ["8888"], target = "0009" 输出：1
 * 解释： 把最后一位反向旋转一次即可 "0000" -> "0009"。
 */
public class DemoBFS_752 {

    @Test
    public void test() {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(openLock(deadends, target));
    }

    /**
     * Description: 解题思路：
     * 1、每次拨动转盘锁，每个转盘都有两种选择：向上拨（数字变大）、向下拨（数字变小）
     * 2、现在转盘锁有4个转盘，转动一次就有8种可能性，这便会组成一个树一样的结构，每个树有8个子节点
     * 3、由于需要获取最少次数，因此就相当于获取最小的层数，因此使用BFS算法
     */
    public int openLock(String[] deadends, String target) {
        List<String> deadendList = Stream.of(deadends).collect(Collectors.toList());
        HashSet<String> visited = new HashSet<>(); // 存储访问过的结果，防止一个转盘上拨下拨出现死循环
        Queue<String> queue = new LinkedList<>();
        String initLock = "0000"; // 锁初始状态
        queue.offer(initLock);
        visited.add(initLock);
        int step = 0;


        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String lock = queue.poll();
                if (target.equals(lock)) return step;
                if (deadendList.contains(lock)) continue; // 跳过死锁的结果
                for (int index = 0; index < initLock.length(); index++) {
                    String up = up(lock, index);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = down(lock, index);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public String up(String lock, int index) {
        char[] chars = lock.toCharArray();
        if (chars[index] == '9') chars[index] = '0';
        else chars[index] += 1;
        return new String(chars);
    }

    public String down(String lock, int index) {
        char[] chars = lock.toCharArray();
        if (chars[index] == '0') chars[index] = '9';
        else chars[index] -= 1;
        return new String(chars);
    }
}
