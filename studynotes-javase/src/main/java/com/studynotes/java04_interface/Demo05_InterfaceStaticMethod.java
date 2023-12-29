package com.studynotes.java04_interface;

import org.junit.jupiter.api.Test;

/**
 * Description: Java 8 新增了接口的 static 静态方法，可以通过接口直接调用接口中的静态方法。
 */
public class Demo05_InterfaceStaticMethod {

    @Test
    void test() {
        MyInterface.method();

        MyInterface myInterface = new MyClass();

        // myInterface.method(); // 接口中的静态方法不能通过实现类的对象调用
    }

    interface MyInterface {

        static void method() {
            System.out.println("interface static method");
        }
    }

    static class MyClass implements MyInterface {

        static void method() {
            System.out.println("class static method");
        }
    }
}
