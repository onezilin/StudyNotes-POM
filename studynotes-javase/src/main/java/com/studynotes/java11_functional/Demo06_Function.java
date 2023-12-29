package com.studynotes.java11_functional;

import java.util.function.Function;

/**
 * Description: 继承 Function 接口，可以扩展 Function 接口的方法，其他函数式接口同理
 */
public class Demo06_Function {

    public static void main(String[] args) {
        FuncSSS f = produce();
        System.out.println(f.apply(11111111));
    }

    static FuncSSS produce() {
        return Object::toString;
    }

    interface FuncSSS extends Function<Integer, String> {
    }
}
