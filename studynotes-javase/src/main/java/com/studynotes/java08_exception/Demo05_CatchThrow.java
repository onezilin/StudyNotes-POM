package com.studynotes.java08_exception;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * Description: catch 捕获异常后，可以使用 throw 重新抛出异常
 * 使用 throws 声明方法可能抛出的异常
 */
public class Demo05_CatchThrow {

    @Test
    @SneakyThrows
    void test() {
        g();
        // h();
    }

    public static void g() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside g(), e.printStackTrace()");
            e.printStackTrace();
            throw e;
        }
        System.out.println("继续执行");
    }

    public static void h() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside h(), e.printStackTrace()");
            e.printStackTrace();
            // 使用 fillInStackTrace() 方法可以将当前调用栈的信息填充到异常对象中
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void f() throws Exception {
        System.out.println("originating the exception in f()");
        throw new Exception("thrown from f()");
    }
}
