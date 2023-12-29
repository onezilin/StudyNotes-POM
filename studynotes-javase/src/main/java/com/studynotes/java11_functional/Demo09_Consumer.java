package com.studynotes.java11_functional;

import java.util.function.BiConsumer;

/**
 * Description: Consumer 用于接收参数，返回 Void 值
 */
public class Demo09_Consumer {

    static void accept(In1 i1, In2 i2) {
        System.out.println("accept()");
    }

    static void someOtherName(In1 i1, In2 i2) {
        System.out.println("someOtherName()");
    }

    public static void main(String[] args) {
        BiConsumer<In1, In2> bic;

        bic = Demo09_Consumer::accept;
        bic.accept(new In1(), new In2());

        bic = Demo09_Consumer::someOtherName;
        bic.accept(new In1(), new In2());
    }

    static class In1 {
    }

    static class In2 {
    }
}
