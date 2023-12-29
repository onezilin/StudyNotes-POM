package com.studynotes.java04_interface;

import org.junit.jupiter.api.Test;

/**
 * Description: Java 8 新增了接口的 default 默认方法，可以通过接口的实现类对象直接调用接口中的默认方法。
 */
public class Demo04_InterfaceDefaultMethod {

    @Test
    void test() {
        MyClass myClass = new MyClass();

        myClass.firstMethod();
        myClass.defaultMethod();
    }

    interface MyInterface {

        void firstMethod();

        /**
         * 默认方法，子类可以重写，也可以不用重写
         */
        default void defaultMethod() {
            System.out.println("newMethod");
        }
    }

    class MyClass implements MyInterface {

        @Override
        public void firstMethod() {
            System.out.println("firstMethod");
        }

        /**
         * 子类可以重写接口中的默认方法
         */
        @Override
        public void defaultMethod() {
            System.out.println("myNewMethod");
        }
    }
}
