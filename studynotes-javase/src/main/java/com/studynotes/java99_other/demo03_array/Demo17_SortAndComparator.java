package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;
import java.util.Collections;

// 若不实现Conparable接口，可以调用默认的方法和Comparator
public class Demo17_SortAndComparator {
    public static void main(String[] args) {
        String[] a = {"Btpenpc", "cuxszgv", "gmeinne", "eloztdv", "ewcippc",
                "ygpoalk", "ljlbynx", "taprwxz", "bhmupju", "cjwzmmr",
                "anmkkyh", "fcjpthl", "skddcat", "jbvlgwc", "mvducuj",
                "ydpulcq", "zehpfmm", "zrxmclh", "qgekgly", "hyoubzl"};

        System.out.println("Before sort" + Arrays.toString(a));
        Arrays.sort(a);
        System.out.println("After sort" + Arrays.toString(a));
        Arrays.sort(a, Collections.reverseOrder());
        System.out.println("Reverse sort" + Arrays.toString(a));
        // 大小写不敏感的排序
        Arrays.sort(a, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sort" + Arrays.toString(a));
    }
}
