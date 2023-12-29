package com.studynotes.java12_stream;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Description: 用于判断流中的元素是否满足某个条件
 * allMatch() 流中每个元素的 Predicate 计算后返回 boolean，在第一个 false 时停止计算
 * anyMatch() 流中任意一个元素的 Predicate 计算后返回 boolean，在第一个 true 时停止计算
 * noneMatch() 流中任意一个元素的 Predicate 计算后返回 boolean，在第一个 true 时停止计算
 */
public class Demo37_Match {
    static void show(Matcher match, int val) {
        System.out.println(
                match.test(
                        IntStream.rangeClosed(1, 9).boxed().peek(n -> System.out.format("%d ", n)),
                        n -> n < val)
        );
    }

    public static void main(String[] args) {
        // Stream::allMatch相当于(stream, predicate) -> stream.allMatch(predicate)
        // 相当于stream::allMatch(predicate)
        //
        show(Stream::allMatch, 10);
        show(Stream::allMatch, 4);
        show(Stream::anyMatch, 2);
        show(Stream::anyMatch, 0);
        show(Stream::noneMatch, 5);
        show(Stream::noneMatch, 0);
    }

    interface Matcher extends BiPredicate<Stream<Integer>, Predicate<Integer>> {

    }
}


