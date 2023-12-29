package com.studynotes.java11_functional;

import java.util.function.Function;

/**
 * Description: 柯里化，将一个多参数的函数转换为一系列单参数函数
 */
public class Demo14_Curry {

    public String uncurried(String a, String b, String c) {
        return a + b + c;
    }

    public Function<String, Function<String, Function<String, String>>> curried() {
        return a -> b -> c -> a + b + c;
    }

    // 柯里化
    Function<String, Function<String, Function<String, String>>> curry = a -> b -> c -> a + b + c;

    // 可以这样看， 每次都是返回一个Function，最后才返回String
    // a -> {
    //     return b -> {
    //         return c-> {
    //             return a + b + c;
    //         }
    //     }
    // }
}
