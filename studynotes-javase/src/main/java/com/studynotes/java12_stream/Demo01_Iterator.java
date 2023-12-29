package com.studynotes.java12_stream;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Description: 普通的遍历方式
 */
public class Demo01_Iterator {
    public static void main(String[] args) {
        Random rand = new Random(47);
        SortedSet<Integer> rints = new TreeSet<>();

        while (rints.size() < 7) {
            int r = rand.nextInt(20);
            if (r < 5) continue;
            rints.add(r);
        }
        System.out.println(rints);
    }
}
