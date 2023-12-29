package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Description: 创建集合对象。集合可以传入可变参数、数组、集合，转变为集合
 */
public class Demo05_InitCollection {
    @Test
    public void initCollection() {

        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        Integer[] moreInts = {6, 7, 8, 9, 10};
        collection.addAll(Arrays.asList(moreInts));

        Collections.addAll(collection, 11, 12, 13, 14, 15);

        Collections.addAll(collection, moreInts);

        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1, 99);

        // Arrays.asList生成的List不可以增删
        //         list.add(21);
    }
}

