package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;
import java.util.SplittableRandom;

// 多维数组的创建及使用
public class Demo04_InitArray {
    public static void main(String[] args) {
        // 直接赋值
        int[][] a = {
                {1, 2, 3,},
                {4, 5, 6,},
        };
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.deepToString(a));

        // 多维数组，只需要指定最外维的数组长度
        int[][][] b = new int[5][][];
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.deepToString(b));

        int[][][] c = new int[2][2][4];
        System.out.println(Arrays.deepToString(a));

        SplittableRandom rand = new SplittableRandom(47);
        // 可以创建多维不规则数组,也就是多维的每个一维数组长度不一致
        int[][][] d = new int[rand.nextInt(7)][][];
        for (int i = 0; i < a.length; i++) {
            d[i] = new int[rand.nextInt(5)][];
            for (int j = 0; j < a[i].length; j++) {
                d[i][j] = new int[rand.nextInt(5)];
                // setAll方法是将数组设置值
                Arrays.setAll(d[i][j], n -> n++);
            }
        }
        System.out.println(Arrays.deepToString(d));
    }
}
