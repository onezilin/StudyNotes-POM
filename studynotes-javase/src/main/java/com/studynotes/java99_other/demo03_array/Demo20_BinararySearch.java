package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// 对于使用了比较器排序后的数组，二分查找时也要传入比较器类
public class Demo20_BinararySearch {
    public static void main(String[] args) {
        String[] a = {"anmkkyh", "bhmupju", "btpenpc", "cjwzmmr", "cuxszgv", "eloztdv", "ewcippc",
                "ezdeklu", "fcjpthl", "fqmlgsh", "gmeinne", "hyoubzl", "jbvlgwc", "jlxpqds",
                "ljlbynx", "mvducuj", "qgekgly", "skddcat", "taprwxz", "uybypgp", "vjsszkn",
                "vniyapk", "vqqakbm", "vwodhcf", "ydpulcq", "ygpoalk", "yskvett", "zehpfmm",
                "zofmmvm", "zrxmclh"};
        Arrays.sort(a, String.CASE_INSENSITIVE_ORDER);
        System.out.println("a:" + Arrays.toString(a));
        int index = Arrays.binarySearch(a, a[10], String.CASE_INSENSITIVE_ORDER);
        System.out.println("Index: " + index + "\n" + a[index]);
    }
}
