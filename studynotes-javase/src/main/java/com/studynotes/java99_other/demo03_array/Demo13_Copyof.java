package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// 使用copyof赋值一个数组的值到另外一个新的数组中,
// 注意：copyof的时候只能向上转型，注意是将值赋值，而不是地址
public class Demo13_Copyof {
    public static final int SZ = 15;

    public static void main(String[] args) {
        int[] a = new int[SZ];
        Arrays.setAll(a, n -> n);
        System.out.println("a:" + Arrays.toString(a));
        int[] b = Arrays.copyOf(a, a.length);
        System.out.println("b:" + Arrays.toString(b));
        // Prove they are distinct arrays:
        Arrays.fill(b, 1);
        // 是否是复制值
        System.out.println("a:" + Arrays.toString(a));
        System.out.println("b:" + Arrays.toString(b));

        int[] c = Arrays.copyOf(a, b.length / 2);
        System.out.println("c:" + Arrays.toString(c));

        int[] d = Arrays.copyOf(a, a.length + 5);
        System.out.println("d:" + Arrays.toString(d));

        Integer[] e = new Integer[SZ];
        Arrays.setAll(e, Integer::new);
        System.out.println("e:" + Arrays.toString(e));

        Integer[] f = Arrays.copyOfRange(e, 4, 12);
        System.out.println("f:" + Arrays.toString(f));

        Sub[] g = new Sub[SZ / 2];
        Arrays.setAll(g, Sub::new);
        System.out.println("g:" + Arrays.toString(g));

        // 向上转型
        Sup[] h = Arrays.copyOf(g, g.length, Sup[].class);
        System.out.println("h:" + Arrays.toString(h));

        Sub[] i = Arrays.copyOf(g, g.length, Sub[].class);
        System.out.println("i:" + Arrays.toString(i));

        Sup[] j = new Sup[SZ / 2];
        Arrays.setAll(j, Sup::new);
        System.out.println("j:" + Arrays.toString(j));

        try {
            // 向下转型报错
            Sub[] d3 = Arrays.copyOf(j, j.length, Sub[].class); // [6]
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

// arrays/ArrayCopying.java
// Demonstrate Arrays.copy() and Arrays.copyOf()
class Sup {
    private int id;

    Sup(int n) {
        id = n;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + id;
    }
}

class Sub extends Sup { // Subclass
    Sub(int n) {
        super(n);
    }
}
