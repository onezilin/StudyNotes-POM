package com.studynotes.java12_stream;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Description: Optional.isPresent() 判断 Optional 中是否有值，
 * 有参的 isPresent() 也可以接受 Consumer 参数，当有值时执行，否则什么也不做
 */
public class Demo21_IsPresent {
    static void test(Optional optString) {
        if (optString.isPresent())
            System.out.println(optString.get());
        else
            System.out.println("Nothing inside!");
    }

    public static void main(String[] args) {
        test(Stream.of("Epithets", "234234", "22222222").findAny());
        test(Stream.<String>empty().findFirst());
    }
}
