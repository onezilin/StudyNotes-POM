package com.studynotes.java12_stream;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Description: concat() 是按顺序组合两个流，将元素加入到一个新的 Stream 中
 * flatMap主要是将Stream扁平化，将扁平化的元素加入到当前流中
 */
public class Demo19_Concat {
    public static void main(String[] args) {
        Random rand = new Random(47);
        IntStream.concat(
                        rand.ints(0, 100)
                                .limit(10)
                                .sorted(), IntStream.of(-1))
                .forEach(i -> System.out.println(i));
    }
}
