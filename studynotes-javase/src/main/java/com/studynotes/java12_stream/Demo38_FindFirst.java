package com.studynotes.java12_stream;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.studynotes.java12_stream.Demo31_ToArray.rands;

/**
 * Description:
 * findFirst() 获取流中第一个元素，转换为 Optional
 * findAny() 获取流中任一个元素，转换为 Optional
 */
public class Demo38_FindFirst {
    public static void main(String[] args) {
        OptionalInt last = IntStream.range(10, 20).reduce((n1, n2) -> n2);
        System.out.println(last.orElse(-1));

        Optional<String> lastobj = Stream.of("one", "two", "three").reduce((n1, n2) -> n2);
        System.out.println(lastobj.orElse("Nothing there!"));

        System.out.println(rands().findFirst().getAsInt());
        System.out.println(rands().parallel().findFirst().getAsInt());
        System.out.println(rands().findAny().getAsInt());
        System.out.println(rands().parallel().findAny().getAsInt());
    }
}
