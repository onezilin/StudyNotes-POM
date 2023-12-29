package com.studynotes.java08_exception;

import org.junit.jupiter.api.Test;

/**
 * Description: catch 捕获异常后，可以使用 throw 重新抛出异常
 * 使用 try-catch 再次捕获异常
 */
public class Demo06_CatchThrow {

    @Test
    void test() {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("Caught in inner try, e.printStackTrace()");
                e.printStackTrace();
                // 直接抛出e的话就相当于就是上一个异常
                // throw e;
                // 这里是抛出一个新的异常,和上一个异常的方法调用栈无关
                throw new TwoException("from inner try");
            }
        } catch (TwoException e) {
            System.out.println("Caught in outer try, e.printStackTrace()");
            e.printStackTrace();
        }
    }

    static class OneException extends Exception {
        OneException(String s) {
            super(s);
        }
    }

    static class TwoException extends Exception {
        TwoException(String s) {
            super(s);
        }
    }

    public void f() throws OneException {
        System.out.println("originating the exception in f()");
        throw new OneException("thrown from f()");
    }
}
