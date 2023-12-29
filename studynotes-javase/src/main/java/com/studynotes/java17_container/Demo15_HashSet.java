package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Description: HashSet使用
 */
public class Demo15_HashSet {
    @Test
    public void setTest() {
        Random rand = new Random(47);
        Set<Double> intset = new HashSet<>();
        // 从结果可以看出HashSet会自动将integer排序
        for (int i = 0; i < 10000; i++) intset.add(rand.nextDouble());
        System.out.println(intset);
    }

    @Test
    public void setTest2() {
        Set<String> colors = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            colors.add("Yellow");
            colors.add("Blue");
            colors.add("Red");
            colors.add("Red");
            colors.add("Orange");
            colors.add("Yellow");
            colors.add("Blue");
            colors.add("Purple");
        }
        System.out.println(colors);
    }
}
