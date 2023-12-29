package com.studynotes.java02_polymorphism;

import org.junit.jupiter.api.Test;

/**
 * Description: 静态方法在多态中的表现，静态方法不具有多态性，即在编译期就确定方法的执行版本
 */
public class Demo02_Static {

    @Test
    public void test01() {
        Parent p = new Child();
        p.staticMethod(); // Parent staticMethod
        p.instanceMethod(); // Child instanceMethod
    }

    static class Parent {

        public static void staticMethod() {
            System.out.println("Parent staticMethod");
        }

        public void instanceMethod() {
            System.out.println("Parent instanceMethod");
        }
    }

    static class Child extends Parent {
        public static void staticMethod() {
            System.out.println("Child staticMethod");
        }

        @Override
        public void instanceMethod() {
            System.out.println("Child instanceMethod");
        }
    }
}
