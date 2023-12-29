package com.studynotes.java17_container;

import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * Description: 测试 HashMap 扩容
 */
public class Demo35_HashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>(64);

        IntStream.range(0, 100000).forEach(i -> map.put(i, "value" + i));

        map.put(null, "value" + 100000);
    }

    static class OneHash {
        @Override
        public int hashCode() {
            return 1;
        }
    }
}
