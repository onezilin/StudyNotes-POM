package com.studynotes.java12_stream;

import lombok.Data;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Description: flatMap() 用于接收 Function 函数，返回 Stream、IntStream 等类型元素
 * flatMap() 会将返回的 Stream 流进行扁平化，也就是将返回的 Stream 中的元素整合到一个 Stream 中，主要用于将多个 Stream 中的元素进行组合
 */
public class Demo18_FaltMap {
    public static void main(String[] args) throws IOException {
        Stream.of(1, 2, 3)
                // 将Integer类型的元素转化为Stream类型的元素
                .map(i -> Stream.of("Gonzo", "Kermit", "Beaker"))
                .map(e -> e.getClass().getName())
                .forEach(System.out::println);

        Stream.of(1, 2, 3)
                // 将产生流的函数应用到每个元素上,然后将每个流进行扁平化为元素,
                .flatMap(i -> Stream.of("Gonzo", "Fozzie", "Beaker"))
                .forEach(System.out::println);

        Random rand = new Random(47);
        Stream.of(1, 2, 3, 4, 5)
                .flatMapToInt(i -> IntStream.concat(
                        rand.ints(0, 100)
                                .limit(i)
                                .sorted(), IntStream.of(-1))) // 这里产生的流,都会被flatMap()扁平化为元素
                .forEach(n -> System.out.format("%d ", n));

        //        Files.lines(Paths.get("Cheese.dat"))
        //                .skip(1) // First (comment) line
        //                .flatMap(line ->
        //                        Pattern.compile("\\W+").splitAsStream(line));// 传入的每行都进行函数操作后转化为流,再被flatMap()扁平化为元素

        List<String> test =
                IntStream.range(0, 3).mapToObj(index -> new MyListTest()).map(MyListTest::getStringList).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(test);
    }

    @Data
    private static class MyListTest {
        List<String> stringList = Arrays.asList("1", "2", "3");
    }
}
