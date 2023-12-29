package com.studynotes.java12_stream;

import java.util.stream.Stream;

/**
 * Description: Stream.of() 可以将可变参数转化为 Stream 对象
 */
public class Demo03_Of {
    public static void main(String[] args) {
        Stream.of(new Bubble(1), "11111", new Bubble(2), new Bubble(3))
                .forEach(System.out::println);

        Bubble[] bubbles = {new Bubble(1), new Bubble(2), new Bubble(3)};
        Stream.of(bubbles).forEach(System.out::println);
    }
}

class Bubble {
    public final int i;

    public Bubble(int n) {
        i = n;
    }

    @Override
    public String toString() {
        return "Bubble(" + i + ")";
    }

    private static int count = 0;

    public static Bubble bubbler() {
        return new Bubble(count++);
    }
}


