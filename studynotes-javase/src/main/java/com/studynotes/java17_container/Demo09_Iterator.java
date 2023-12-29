package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Description: 使用集合内部的迭代和for的区别，在for中进行删除操作会报错
 */
public class Demo09_Iterator {

    @Test
    public void iteratorTest() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8);

        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            Integer temp = iter.next();
            iter.next();
            System.out.println(temp);
        }

        Iterator<Integer> iter1 = list.iterator();
        while (iter1.hasNext()) {
            Integer temp = iter1.next();
            iter1.remove();
            System.out.println(temp);
        }
    }

    @Test
    public void forEachTest() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        int i = 0;
        for (Integer s : list) {
            System.out.println(s);
            list.remove(i);
            i++;
        }
    }
}
