package com.studynotes.java11_functional;

import java.util.function.Function;

/**
 * Description: 使用 Lambda 表达式实现函数式接口
 */
public class Demo07_Function {
    static Function<I, O> transform(Function<I, O> in) {
        return in.andThen(o -> {
            System.out.println(o);
            return o;
        });
    }

    public static void main(String[] args) {
        Function<I, O> f1 = i -> {
            System.out.println(i);
            return new O();
        };

        Function<I, O> f2 = transform(f1);
        O o = f2.apply(new I());
    }

    static class I {
        @Override
        public String toString() {
            return "I";
        }
    }

    static class O {
        @Override
        public String toString() {
            return "O";
        }
    }
}
