package com.studynotes.java12_stream;

import java.util.Iterator;

/**
 * Description: iterator() 用来将 Stream 转换为 Iterator
 */
public class Demo30_Iterator {
    public static void main(String[] args) {
        Iterator<Character> capChars = new Demo28_RandomPair().rand
                .ints(65, 91)
                .mapToObj(i -> (char) i)
                .iterator();
    }
}
