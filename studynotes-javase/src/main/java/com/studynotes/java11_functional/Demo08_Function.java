package com.studynotes.java11_functional;

import java.util.function.Function;

/**
 * Description: Function 接口中提供 compose() 和 andThen() 方法，其他接口同理
 */
public class Demo08_Function {

    static Function<String, String> f1 = s -> {
        System.out.println(s);
        return s.replace('A', '_');
    };

    static Function<String, String> f2 = s -> s.substring(3);

    static Function<String, String> f3 = s -> s.toLowerCase();

    static Function<String, String> f4 = f1.compose(f2).andThen(f3);

    public static void main(String[] args) {
        System.out.println(f4.apply("GO AFTER ALL AMBULANCES"));
    }
}
