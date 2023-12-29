package com.studynotes.java12_stream;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Description: Opional 的主要方法
 * <p>
 * ifPresent()：若值不为空，则执行 Consumer 函数
 * orElse()：若值为空，则返回参数
 * orElseGet()：若值为空，则使用 Supplier 函数生成一个值
 * orElseThrow()：若值为空，则使用 Supplier 函数生成一个异常
 * get()：若值不为空，则返回值，否则抛出异常
 */
public class Demo22_Get {
    // 警告是因为Optional不是用来作为参数传递的
    private static void basics(Optional<String> optString) {
        if (optString.isPresent())
            System.out.println(optString.get());
        else
            System.out.println("Nothing inside!");
    }

    private static void ifPresent(Optional<String> optString) {
        optString.ifPresent(System.out::println);
    }

    private static void orElse(Optional<String> optString) {
        System.out.println(optString.orElse("Nada"));
    }

    private static void orElseGet(Optional<String> optString) {
        System.out.println(
                optString.orElseGet(() -> "Generated"));
    }

    private static void orElseThrow(Optional<String> optString) {
        try {
            System.out.println(optString.orElseThrow(
                    () -> new Exception("Supplied")));
        } catch (Exception e) {
            System.out.println("Caught " + e);
        }
    }

    private static void test(String testName, Consumer<Optional<String>> cos) {
        System.out.println(" === " + testName + " === ");
        cos.accept(Stream.of("Epithets").findFirst());
        cos.accept(Stream.<String>empty().findFirst());
    }

    public static void main(String[] args) {
        test("basics", Demo22_Get::basics);
        test("ifPresent", Demo22_Get::ifPresent);
        test("orElse", Demo22_Get::orElse);
        test("orElseGet", Demo22_Get::orElseGet);
        test("orElseThrow", Demo22_Get::orElseThrow);
    }
}
