package com.studynotes.java08_exception;

/**
 * Description: catch 捕获异常后，可以使用 throw 重新抛出异常
 * 将异常封装成 RuntimeException 抛出，RuntimeException 是非检查异常，可以不用在方法签名中声明异常
 */
public class Demo07_CatchThrow {

    public void g() {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside g(), e.printStackTrace()");
            e.printStackTrace();
            // 封装成 RuntimeException
            throw new RuntimeException(e);
        }
    }

    public void f() throws Demo06_CatchThrow.OneException {
        System.out.println("originating the exception in f()");
        throw new Demo06_CatchThrow.OneException("thrown from f()");
    }
}
