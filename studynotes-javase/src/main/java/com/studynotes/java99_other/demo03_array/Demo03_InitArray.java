package com.studynotes.java99_other.demo03_array;

// 创建数组对象的几种方式
public class Demo03_InitArray {
    public static void main(String[] args) {
        int[] a = new int[5];
        int[] b = {1, 2, 3};
        // 只有第一次初始化时才运行聚合赋值
        //        b = {1, 2, 3};
        int[] c = new int[]{1, 2, 3};
    }
}
