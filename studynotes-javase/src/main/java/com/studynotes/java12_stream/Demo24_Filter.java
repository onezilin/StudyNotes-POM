package com.studynotes.java12_stream;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Description: filter()，接收 Predicate 参数，
 * 当 Optional 中的 value 满足 Predicate 时返回 Optional，否则调用 empty() 生成 value 为 null 的 Optional
 */
public class Demo24_Filter {
    static String[] elements = {
            "Foo", "", "Bar", "Baz", "Bingo"
    };

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Predicate<String> pred) {
        System.out.println(" ---( " + descr + " )---");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(
                    testStream()
                            // 即使跳转超出元素的个数,不会报错,会返回一个null值
                            .skip(i)
                            .findFirst()
                            .filter(pred));
        }
    }

    public static void main(String[] args) {
        test("true", str -> true);
        test("false", str -> false);
        test("str != \"\"", str -> str != "");
        test("str.length() == 3", str -> str.length() == 3);
        test("startsWith(\"B\")", str -> str.startsWith("B"));
    }
}
