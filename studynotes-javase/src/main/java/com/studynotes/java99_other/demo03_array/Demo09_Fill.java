package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// Arrays工具类的fill方法，给数组中元素填充值
public class Demo09_Fill {
    public static int size = 6;

    public static void main(String[] args) {
        boolean[] a1 = new boolean[size];
        Arrays.fill(a1, true);
        System.out.println("a1:" + Arrays.toString(a1));

        byte[] a2 = new byte[size];
        Arrays.fill(a2, (byte) 11);
        System.out.println("a2:" + Arrays.toString(a2));

        char[] a3 = new char[size];
        Arrays.fill(a3, 'x');
        System.out.println("a3:" + Arrays.toString(a3));

        short[] a4 = new short[size];
        Arrays.fill(a4, (short) 17);
        System.out.println("a4:" + Arrays.toString(a4));

        int[] a5 = new int[size];
        Arrays.fill(a5, 19);
        System.out.println("a5:" + Arrays.toString(a5));

        long[] a6 = new long[size];
        Arrays.fill(a6, 23);
        System.out.println("a6:" + Arrays.toString(a6));

        float[] a7 = new float[size];
        Arrays.fill(a7, 29);
        System.out.println("a7:" + Arrays.toString(a7));

        double[] a8 = new double[size];
        Arrays.fill(a8, 47);
        System.out.println("a8:" + Arrays.toString(a8));

        String[] a9 = new String[size];
        Arrays.fill(a9, "Hello");
        System.out.println("a9:" + Arrays.toString(a9));

        // 指定范围
        Arrays.fill(a9, 3, 5, "World");
        System.out.println("a4:" + Arrays.toString(a4));
    }
}

