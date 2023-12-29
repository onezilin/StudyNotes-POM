package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// 使用setAll和operator函数对数组进行操作,
// fill是对整个数组操作，setAll根据下标对每个元素操作
public class Demo10_SetAll {
    public static final int SZ = 8;
    static int val = 1;
    static char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    static char getChar(int n) {
        return chars[n];
    }

    public static void main(String[] args) {
        int[] a = new int[SZ];
        Arrays.setAll(a, n -> n);
        System.out.println("a:" + Arrays.toString(a));
        Arrays.setAll(a, n -> val++); // [2]
        System.out.println("a:" + Arrays.toString(a));

        long[] b = new long[SZ];
        Arrays.setAll(b, n -> n);
        System.out.println("b:" + Arrays.toString(b));
        Arrays.setAll(b, n -> val++);
        System.out.println("b:" + Arrays.toString(b));

        double[] c = new double[SZ];
        Arrays.setAll(c, n -> n);
        System.out.println("c:" + Arrays.toString(c));
        Arrays.setAll(c, n -> val++);
        System.out.println("c:" + Arrays.toString(c));

        Bob[] d = new Bob[SZ];
        Arrays.setAll(d, Bob::new); // [3]
        System.out.println("a:" + Arrays.toString(a));

        Character[] e = new Character[SZ];
        Arrays.setAll(e, n -> 65 + n); // [4]
        System.out.println("a:" + Arrays.toString(a));
    }
}

class Bob {
    final int id;

    Bob(int n) {
        id = n;
    }

    @Override
    public String toString() {
        return "Bob" + id;
    }
}
