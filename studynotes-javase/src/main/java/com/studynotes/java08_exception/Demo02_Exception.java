package com.studynotes.java08_exception;

import org.junit.jupiter.api.Test;

// Exception的几种常用方法
public class Demo02_Exception {

    @Test
    void test() {
        try {
            throw new Exception("My Exception");
        } catch (Exception e) {
            // 获取有参构造传入的异常信息
            System.out.println("getMessage():" + e.getMessage());
            // 子类可以重写此方法，不重写时返回getMessage()的值
            System.out.println("getLocalizedMessage():" + e.getLocalizedMessage());
            // 返回异常 toString() 的值
            System.out.println("toString():" + e);
            // 输出堆栈跟踪数据等信息
            e.printStackTrace(System.out);
        }
        System.out.println("继续执行");
    }
}
