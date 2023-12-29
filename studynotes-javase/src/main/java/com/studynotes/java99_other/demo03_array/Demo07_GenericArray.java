package com.studynotes.java99_other.demo03_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 将数组进行强转类型，实现泛型功能
public class Demo07_GenericArray {
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        // 将List强转为带泛型的List，间接实现泛型的效果
        ls = (List<String>[]) la;
        ls[0] = new ArrayList<>();

        // 不能将List<Integer>转型为List<String>，
        //        ls[1] = new ArrayList<Integer>();

        Object[] objects = ls;
        // 向上转型
        objects[1] = new ArrayList<>();

        // 将List转型为List<BerylliumSphere>
        List<BerylliumSphere>[] spheres = (List<BerylliumSphere>[]) new List[10];
        // 不能这样转型
        //        List<BerylliumSphere>[] spheres = new List<BerylliumSphere>[10];
        Arrays.setAll(spheres, n -> new ArrayList<>());
    }
}
