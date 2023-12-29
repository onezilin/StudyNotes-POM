package com.studynotes.java99_other.demo03_array;

import java.util.Arrays;

// binararySearch需要先排序查找，
public class Demo19_BinararySearch {
    public static void main(String[] args) {
        int[] a = {125, 267, 635, 650, 1131, 1506, 1634, 2400, 2766,
                3063, 3768, 3941, 4720, 4762, 4948, 5070, 5682,
                5807, 6177, 6193, 6656, 7021, 8479, 8737, 9954};
        Arrays.sort(a);
        System.out.println("Sorted array" + Arrays.toString(a));

        int r = 635;
        //
        int location = Arrays.binarySearch(a, r);
        if (location >= 0) {
            System.out.println("Location of " + r + " is " + location + ", a[" + location + "] is " + a[location]);
        }
    }
}
