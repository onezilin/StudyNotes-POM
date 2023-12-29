package com.studynotes.java17_container;

/**
 * Description: 测试 HashMap 有参构造时的容量计算
 */
public class Demo34_HashMap {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    // 会将传入的参数转换为大于等于该参数的最小的2的幂次方
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(17));
    }
}
