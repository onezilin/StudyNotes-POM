package com.studynotes.java07_generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 使用ArrayList替代数组的功能
 */
public class Demo10_SolveErasure {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // 1、泛型强转
        try {
            gia = (Generic<Integer>[]) new Object[SIZE];
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
        ///2、泛型强转
        gia = (Generic<Integer>[]) new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        gia[0] = new Generic<>();

        // 泛型是Integer，再传其他类型会抛异常
        //        gia[1] = new Object();

        // 使用ArrayList替代泛型
        ListOfGenerics<Integer> gia3 = new ListOfGenerics<>();
    }

    static class ListOfGenerics<T> {
        private List<T> array = new ArrayList<>();

        public void add(T item) {
            array.add(item);
        }

        public T get(int index) {
            return array.get(index);
        }
    }

    static class Generic<T> {
    }
}


