package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.SplittableRandom;

// Arrays.sort()方法进行排序,
// Comparable 和 Compator的区别:
// Comparable是对象自身和其他对象进行比较;
// Compator是传入两个对象进行比较,解耦
// 返回-1则表示第一个参数小，要排在前面

public class Demo16_SortAndComparator {
    public static void main(String[] args) {
        CompType[] a = new CompType[12];
        Arrays.setAll(a, n -> CompType.get());
        System.out.println("Before sorting:" + Arrays.toString(a));
        // 仔细看源码,会在binarySort方法中看到调用了compareTo方法
        Arrays.sort(a);
        System.out.println("After sorting:" + Arrays.toString(a));

        // 调用Comparable
        CompType[] b = new CompType[12];
        Arrays.setAll(b, n -> CompType.get());
        System.out.println("Before sorting:" + Arrays.toString(b));
        // 这里则在binarySort调用compare方法，倒序
        Arrays.sort(b, Collections.reverseOrder());
        System.out.println("After sorting:" + Arrays.toString(b));


        // 调用Comparator
        CompType[] c = new CompType[12];
        Arrays.setAll(c, n -> CompType.get());
        System.out.println("Before sorting:" + Arrays.toString(c));
        Arrays.sort(c, new CompTypeComparator());
        System.out.println("After sorting:" + Arrays.toString(c));
    }
}

class CompType implements Comparable<CompType> {
    private static int count = 1;
    private static SplittableRandom r = new SplittableRandom(47);
    int i;
    int j;

    public CompType(int n1, int n2) {
        i = n1;
        j = n2;
    }

    public static CompType get() {
        return new CompType(r.nextInt(100), r.nextInt(100));
    }

    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        String result = "[i = " + i + ", j = " + j + "]";
        if (count++ % 3 == 0) result += "\n";
        return result;
    }

    @Override
    public int compareTo(CompType rv) {
        return (i < rv.i ? -1 : (i == rv.i ? 0 : 1));
    }
}


class CompTypeComparator implements Comparator<CompType> {
    public int compare(CompType o1, CompType o2) {
        return (o1.j < o2.j ? -1 : (o1.j == o2.j ? 0 : 1));
    }
}

