package com.studynotes.java12_stream;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Description: map() 对流中元素进行操作，返回其他类型，参数类型为 Function 函数
 */
public class Demo16_Map {
    static String[] elements = {"12", "", "23", "45"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Function<String, String> func) {
        System.out.println(" ---( " + descr + " )---");
        testStream()
                .map(func)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        test("add brackets", s -> "[" + s + "]");
        test("Increment", s -> {
                    try {
                        return Integer.parseInt(s) + 1 + "";
                    } catch (NumberFormatException e) {
                        return s;
                    }
                }
        );
        test("Replace", s -> s.replace("2", "9"));
        test("Take last digit", s -> s.length() > 0 ? s.charAt(s.length() - 1) + "" : s);

        Stream.of("5", "7", "9")
                .mapToInt(Integer::parseInt)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Stream.of("17", "19", "23")
                .mapToLong(Long::parseLong)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Stream.of("17", "1.9", ".23")
                .mapToDouble(Double::parseDouble)
                .forEach(n -> System.out.println(n));
    }
}
