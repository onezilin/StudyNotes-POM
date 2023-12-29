package com.studynotes.java12_stream;

import static java.util.stream.IntStream.range;

/**
 * Description: foreach() 用于遍历流中的元素
 */
public class Demo07_IntStreamRange {

    static void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }

    static void hi() {
        System.out.println("Hi!");
    }

    public static void main(String[] args) {
        repeat(3, () -> System.out.println("Looping!"));
        repeat(2, Demo07_IntStreamRange::hi);
    }
}


