package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// 数组是保存其他对象引用的对象,有固定长度,会给其中包含的其他对象的引用赋初值
public class Demo02_Array {
    public static void main(String[] args) {
        // Arrays of objects:
        BerylliumSphere[] a; // Uninitialized local
        BerylliumSphere[] b = new BerylliumSphere[5];
        System.out.println("b: " + Arrays.toString(b));

        BerylliumSphere[] c = new BerylliumSphere[4];
        for (int i = 0; i < c.length; i++)
            if (c[i] == null)
                c[i] = new BerylliumSphere();

        // 直接赋值，只允许在数组第一次初始化时使用
        BerylliumSphere[] d = {new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()};
        // 初始化
        a = new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()};

        System.out.println("a.length = " + a.length);
        System.out.println("b.length = " + b.length);
        System.out.println("c.length = " + c.length);
        System.out.println("d.length = " + d.length);
        a = d;
        System.out.println("a.length = " + a.length);

        int[] e;
        int[] f = new int[5];
        // 数组中的每个元素初始值都是0
        System.out.println("f:" + Arrays.toString(f));
        char[] x = new char[5];
        // 数组中的每个元素初始值都是''
        System.out.println("x:" + Arrays.toString(x));
        String[] y = new String[5];
        // 数组中的每个元素初始值都是null
        System.out.println("y:" + Arrays.toString(y));

        int[] g = new int[4];
        for (int i = 0; i < g.length; i++) g[i] = i * i;
        int[] h = {11, 47, 93};

        System.out.println("f.length = " + f.length);
        System.out.println("g.length = " + g.length);
        System.out.println("h.length = " + h.length);
        e = h;
        System.out.println("e.length = " + e.length);
        e = new int[]{1, 2};
        System.out.println("e.length = " + e.length);
    }
}
