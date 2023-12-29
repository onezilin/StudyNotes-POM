package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.stream.IntStream;

/**
 * Description: 测试 accessOrder
 * accessOrder 为 true 时，每次 get 后，会将该元素移动到链表尾部
 */
public class Demo36_LinkedHashMap {

    @Test
    public void testNotAccessOrder() {
        LinkedHashMap<Integer, String> notAccessOrder = new LinkedHashMap<>(16, 0.75f, false);
        IntStream.range(0, 5).forEach(i -> notAccessOrder.put(i, "value" + i));
        System.out.println(notAccessOrder);
        notAccessOrder.get(3);
        System.out.println(notAccessOrder);
    }

    @Test
    public void testAccessOrder() {
        LinkedHashMap<Integer, String> accessOrder = new LinkedHashMap<>(16, 0.75f, true);
        IntStream.range(0, 5).forEach(i -> accessOrder.put(i, "value" + i));
        System.out.println(accessOrder);
        accessOrder.get(3);
        System.out.println(accessOrder);
    }

    // accessOrder 为 true 时，每次 get 后，都是结构性修改，会导致 modCount 增加
    @Test
    public void testModCount() {
        LinkedHashMap<Integer, String> accessOrder = new LinkedHashMap<>(16, 0.75f, true);
        IntStream.range(0, 5).forEach(i -> accessOrder.put(i, "value" + i));

        for (Integer i : accessOrder.keySet()) {
            System.out.println(accessOrder.get(i));
        }
    }
}
