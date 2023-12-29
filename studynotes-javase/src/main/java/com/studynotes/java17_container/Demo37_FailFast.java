package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * Description: 测试 fail-fast 机制，即在迭代过程中，如果发生结构性修改，会抛出 ConcurrentModificationException
 */
public class Demo37_FailFast {

    static ArrayList<Integer> list = new ArrayList<>();

    static {
        IntStream.range(0, 10).forEach(list::add);
    }

    @Test
    public void testFailFast() {
        for (Integer i : list) {
            System.out.println(i);
            list.remove(i);
        }
    }

    @Test
    public void testFailFast2() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            list.remove(i);
        }
    }

    @Test
    public void testFailFast3() {
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            Integer temp = iter.next();
            iter.remove();
            System.out.println(temp);
        }
    }
}
