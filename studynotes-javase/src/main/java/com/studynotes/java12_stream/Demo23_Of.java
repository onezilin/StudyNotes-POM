package com.studynotes.java12_stream;

import java.util.Optional;

/**
 * Description: 生成OPtional类的几种方式：
 * <p>
 * empty()，生成一个值为 null 的 Optional
 * of(value)，将 value 值包装成 Optional，在 value 为 null 时报错
 * ofNullable(value)，当 value 值不为空时包装成 Optional，为 null 时，调用 empty 生成 value 为 null 的 Optional
 */
public class Demo23_Of {
    static void test(String testName, Optional<String> opt) {
        System.out.println(" === " + testName + " === ");
        System.out.println(opt.orElse("Null"));
    }

    public static void main(String[] args) {
        test("empty", Optional.empty());
        test("of", Optional.of("Howdy"));
        try {
            test("of", Optional.of(null)); // 为空时会抛异常
        } catch (Exception e) {
            System.out.println(e);
        }
        test("ofNullable", Optional.ofNullable("Hi"));// 为空时生成Optional.empty
        test("ofNullable", Optional.ofNullable(null));
    }
}

