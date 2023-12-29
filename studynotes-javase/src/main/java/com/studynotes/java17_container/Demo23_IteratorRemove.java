package com.studynotes.java17_container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// 在List的Iterator中调用remove，查看是否报错
public class Demo23_IteratorRemove {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, arr);

        iteratorDemoe(list);
        forDemo(list);
        forDemo2(list);
    }

    public static void iteratorDemoe(List<Integer> oldList) {
        List<Integer> list = new ArrayList<>(oldList);
        Iterator iterator = list.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            //            list.remove(2);
            //            需要调用迭代器自己的remove()
            iterator.remove();

        }
        System.out.println("----------长度：" + list.size() + "------------");
    }

    public static void forDemo(List<Integer> oldList) {
        List<Integer> list = new ArrayList<>(oldList);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            // 例如：删除index = 0后，2就被移动到index = 0的值上，导致index = 1的值为3
            list.remove(i);
        }
        System.out.println("----------长度：" + list.size() + "------------");
    }

    public static void forDemo2(List<Integer> oldList) {
        List<Integer> list = new ArrayList<>(oldList);
        int size = list.size();
        // 超出长度后报错
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
            list.remove(i);
        }
        System.out.println("----------长度：" + list.size() + "------------");
    }
}
