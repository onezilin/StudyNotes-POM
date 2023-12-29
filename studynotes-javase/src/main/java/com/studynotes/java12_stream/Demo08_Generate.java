package com.studynotes.java12_stream;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: Stream.generate() 方法接受一个 Supplier<T> 类型的Lambda表达式，用于生成无限长度的 Stream
 * <p>
 * 由于generate生成的流是无限的，需要条件 limit() 用于限制 Stream 传入管道的元素个数
 */
public class Demo08_Generate {
    public static class Demo01 {
        public static void main(String[] args) {
            Stream.generate(() -> "duplicate")
                    .limit(3)
                    .forEach(System.out::println);
        }
    }

    public static class Demo02 {
        public static void main(String[] args) {
            Stream.generate(Bubble::bubbler)
                    .limit(5)
                    .forEach(System.out::println);
        }
    }

    public static class Demo03 implements Supplier<String> {
        Random rand = new Random(47);
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        public String get() {
            // random.netInt边界内的随机数
            return "" + letters[rand.nextInt(letters.length)];
        }

        public static void main(String[] args) {
            String word = Stream.generate(new Demo03())
                    .limit(30)
                    .collect(Collectors.joining());
            System.out.println(word);
        }
    }
}
