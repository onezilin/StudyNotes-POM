package com.studynotes.java08_exception;

import org.junit.jupiter.api.Test;

/**
 * Description: 继承 Exception 类，自定义异常类
 */
public class Demo01_CustomException {

    @Test
    void test() {
        try {
            throwMyException();
        } catch (MyException e) {
            System.out.println("Caught it!");
            e.printStackTrace();
        }
    }

    void throwMyException() throws MyException {
        System.out.println("Throw SimpleException");
        throw new MyException("异常由 throwMyException() 方法抛出");
    }

    static class MyException extends Exception {
        public MyException() {
            super();
        }

        public MyException(String msg) {
            super(msg);
        }

        public MyException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }
}
