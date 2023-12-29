package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * Description: 使用for-in遍历Map,因为Map没有实现Iterable接口,所以得使用键或值的set来遍历
 */
public class Demo22_MapIterator {
    @Test
    public void mapIterator() {
        for (Map.Entry entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
