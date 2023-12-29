package com.studynotes.java12_stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Description: 获取 Optional 对象
 * <p>
 * findFirst：获取流中第一个元素，封装为 Optional
 * findAny：将流中任一个元素，封装为 Optional
 * max：传入一个 Comparator，获取最大值，返回值为 Optional
 * min：传入 Comparator，获取最小值，返回值为 Optional
 */
public class Demo20_StreamToOptional {
    public static void main(String[] args) {
        System.out.println(Stream.<Object>empty()
                .findFirst());

        System.out.println(Stream.of("12", "23")
                .findFirst());

        // findFirst(), max(), min(), reduce(), average()返回一个Optional对象
        System.out.println(Stream.<String>empty()
                .findFirst());

        System.out.println(Stream.<String>empty()
                .findAny());

        System.out.println(Stream.<String>empty()
                .max(String.CASE_INSENSITIVE_ORDER));

        System.out.println(Stream.<String>empty()
                .min(String.CASE_INSENSITIVE_ORDER));

        System.out.println(Stream.<String>empty()
                .reduce((s1, s2) -> s1 + s2));

        System.out.println(IntStream.empty()
                .average());
    }
}
