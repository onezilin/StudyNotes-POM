package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * Description: jdk5 引入了Iterable的接口，其中包含一个能够生成Iterator的iterator()方法，for-in使Iterable遍历序列
 * 任何实现了Iterable的类都可以使用for-in遍历
 */
public class Demo21_CustomIterator {
    class IterableClass implements Iterable<String> {
        String[] words = ("And that is how " + "we know the Earth to be banana-shaped.").split(" ");

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < words.length;
                }

                @Override
                public String next() {
                    return words[index++];
                }

                @Override
                public void remove() { // Not implemented
                    throw new UnsupportedOperationException();
                }
            };
        }


    }

    @Test
    public void iteratorTest() {
        for (String s : new IterableClass())
            System.out.print(s + " ");
    }
}
