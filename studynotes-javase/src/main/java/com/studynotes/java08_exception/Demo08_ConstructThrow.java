package com.studynotes.java08_exception;

/**
 * Description: 除了普通方法 throws 异常外，构造方法也可以 throws 异常
 */
public class Demo08_ConstructThrow {

    public static void main(String[] args) {
        try {
            MyException myException = new MyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static class MyException {
        public MyException() throws Exception {
            throw new Exception("MyException");
        }
    }
}
