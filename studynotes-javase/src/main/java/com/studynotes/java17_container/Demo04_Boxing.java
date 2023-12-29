package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Description: 对基本类型自动封装
 */
public class Demo04_Boxing {
    @Test
    public void boxing() {
        Collection<Integer> c = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 自动封装
            c.add(i);
        }
        for (Integer i : c)
            System.out.print(i + ", ");
    }
}

