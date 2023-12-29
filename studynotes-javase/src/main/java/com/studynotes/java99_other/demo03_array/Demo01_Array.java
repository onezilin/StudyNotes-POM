package com.studynotes.java99_other.demo03_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 数组和集合的基本比较，数组大小创建时就指定了，不可改变
// 数组相比于集合唯一的优势就是效率
public class Demo01_Array {
    public static int size = 10;

    public static void main(String[] args) {
        BerylliumSphere[] spheres = (BerylliumSphere[]) IntStream.range(0, size).boxed()
                .map(i -> new BerylliumSphere())
                .toArray();
        System.out.println(spheres[4]);

        List<BerylliumSphere> sphereList = IntStream.range(0, size).boxed()
                .map(i -> new BerylliumSphere())
                .collect(Collectors.toList());
        System.out.println(sphereList.get(4));

        int[] integers = {0, 1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(integers));
        System.out.println(integers[4]);

        List<Integer> intList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        intList.add(97);
        System.out.println(intList);
        System.out.println(intList.get(4));
    }
}

class BerylliumSphere {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Sphere " + id;
    }
}
