package com.studynotes.java08_exception;

import org.junit.jupiter.api.Test;

/**
 * Description: 获取异常的调用栈轨迹
 */
public class Demo04_StackTrace {

    @Test
    void tese() {
        f();
        System.out.println("*******");
        g();
        System.out.println("*******");
        h();
    }

    static void f() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            // 获取调用栈轨迹
            for (StackTraceElement ste : e.getStackTrace())
                System.out.println(ste.getMethodName());
        }
    }

    static void g() {
        f();
    }

    static void h() {
        g();
    }
}
