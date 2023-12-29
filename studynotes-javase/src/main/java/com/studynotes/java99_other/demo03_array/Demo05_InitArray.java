package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

public class Demo05_InitArray {
    public static void main(String[] args) {
        int[][] a = new int[4][];
        int[][][][] b = new int[][][][]{{{{}}}}; // 相当于

        // 直接赋值初始化数组
        BerylliumSphere[][] spheres = {
                {new BerylliumSphere(), new BerylliumSphere()},
                {new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere()},
                {new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere(),
                        new BerylliumSphere(), new BerylliumSphere()},
        };
        System.out.println(Arrays.deepToString(spheres));

        // 自动装箱
        Integer[][] d = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
        };
        System.out.println(Arrays.deepToString(d));
    }
}
