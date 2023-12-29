package com.studynotes.java12_stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Description: flatMap() 和 map() 写起来基本一致，但是返回值不同：
 * map() 返回一个 Optional，flatMap() 返回具体的值
 */
public class Demo26_FlatMap {
    static String[] elements = {"12", "", "23", "45"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr,
                     Function<String, Optional<String>> func) {
        System.out.println(" ---( " + descr + " )---");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(
                    testStream()
                            .skip(i)
                            .findFirst()
                            .flatMap(func));// flatMap不会将返回结果包装成Optional,而是Object,需要自己在函数中显示地返回Optional类
        }
    }

    public static void main(String[] args) {
        Optional.of("sss").flatMap(s -> Optional.of("[" + s + "]"));


        test("Add brackets",
                s -> Optional.of("[" + s + "]"));
        test("Increment", s -> {
            try {
                return Optional.of(
                        Integer.parseInt(s) + 1 + "");
            } catch (NumberFormatException e) {
                return Optional.of(s);
            }
        });
        test("Replace",
                s -> Optional.of(s.replace("2", "9")));
        test("Take last digit",
                s -> Optional.of(s.length() > 0 ?
                        s.charAt(s.length() - 1) + ""
                        : s));
    }
}
