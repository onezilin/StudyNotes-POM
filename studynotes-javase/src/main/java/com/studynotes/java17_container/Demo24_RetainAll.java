package com.studynotes.java17_container;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 取两个集合的交集
 */
public class Demo24_RetainAll {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("apple");
        list1.add("banana");
        list1.add("orange");

        List<String> list2 = new ArrayList<>();
        list2.add("banana");
        list2.add("orange");
        list2.add("watermelon");

        List<String> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);
        System.out.println(intersection);
    }
}
