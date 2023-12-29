package com.studynotes.java12_stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Description: reduce() 用于计算，将 Stream 中元素反复结合起来，得到一个值
 */
@Slf4j
public class Demo36_Reduce {
    public static void main(String[] args) {
        Frobnitz frobnitz = Stream.generate(Frobnitz::supply)
                .limit(10)
                .peek(x -> System.out.println("原始流:" + x))
                // 第 1 个参数是初始值，
                // 第 2 个参数是 BinaryOperator，也就是计算规则
                .reduce(new Frobnitz(100), (fr0, fr1) -> {
                    System.out.println("参数1" + fr0);
                    System.out.println("参数2" + fr1);
                    return fr0.size > 50 ? fr0 : fr1;
                });
        System.out.println(frobnitz);

        System.out.println("---------------------------");

        Stream.generate(Frobnitz::supply)
                .limit(10)
                .peek(x -> System.out.println("原始流:" + x))
                .reduce((fr0, fr1) -> {
                    System.out.println("参数1" + fr0);
                    System.out.println("参数2" + fr1);
                    return fr0.size > 50 ? fr0 : fr1;
                }).ifPresent(System.out::println);
    }

    @Test
    public void sumTest() {
        int sum = Stream.of(1, 2, 3).reduce(0, (a, b) -> a + b);
        log.info("sum: {}", sum);
    }

    // streams/Reduce.java
    // 可以使用reduce 返回一个Optional或一个类的实例
    static class Frobnitz {
        int size;

        Frobnitz(int sz) {
            size = sz;
        }

        @Override
        public String toString() {
            return "Frobnitz(" + size + ")";
        }

        // Generator:
        static Random rand = new Random(47);
        static final int BOUND = 100;

        static Frobnitz supply() {
            return new Frobnitz(rand.nextInt(BOUND));
        }
    }
}


