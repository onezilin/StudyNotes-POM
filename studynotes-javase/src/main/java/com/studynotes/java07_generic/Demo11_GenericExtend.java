package com.studynotes.java07_generic;

import org.junit.jupiter.api.Test;

/**
 * Description: 泛型边界的扩展及使用, 可以继承多重边界 `<T extends 类 & 接口 & 接口>`，然后可以使用边界的方法和变量
 */
public class Demo11_GenericExtend {

    @Test
    void test() {
        GenericClass<ActualClass> genericClass = new GenericClass<>();
    }

    static class ActualClass extends MyClass implements MyInterface1, MyInterface2 {

        @Override
        public void method1() {
            System.out.println("MyInterface1 的 method1 方法");
        }

        @Override
        public void method2() {
            System.out.println("MyInterface2 的 method2 方法");
        }
    }

    static class GenericClass<T extends MyClass & MyInterface1 & MyInterface2> {

        private T t;

        void test() {
            System.out.println(t.a);
            t.method1();
            t.method2();
        }
    }

    interface MyInterface1 {
        void method1();
    }

    interface MyInterface2 {
        void method2();
    }

    static class MyClass {
        int a;
    }
}

