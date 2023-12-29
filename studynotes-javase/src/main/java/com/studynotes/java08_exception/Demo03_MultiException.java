package com.studynotes.java08_exception;

import org.junit.jupiter.api.Test;

/**
 * Description: try-catch 中多重捕获的书写方式
 */
public class Demo03_MultiException {

    // 原本写法
    @Test
    void test1() {
        try {
            x();
        } catch (EBase1 e) {
            // ...
        } catch (EBase2 e) {
            // ...
        } catch (EBase3 e) {
            // ...
        }
    }

    // 从 Java 7 开始，可以使用 | 捕获多个异常
    void test2() {
        try {
            x();
        } catch (EBase1 | EBase2 | EBase3 e) {
            // ...
        }
    }

    // 多个异常也可以任意组合
    void test3() {
        try {
            x();
        } catch (EBase1 | EBase2 e) {
            // ...
        } catch (EBase3 e) {
            // ...
        }
    }

    void x() throws EBase1, EBase3, EBase2 {
    }

    static class EBase1 extends Exception {
    }

    static class EBase2 extends Exception {
    }

    static class EBase3 extends Exception {
    }
}
