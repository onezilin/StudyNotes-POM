package com.studynotes.java01_basis.demo04_Access.access;

/**
 * Description:
 * public、protected、default、private 修饰符能够修饰属性、方法、构造器、内部类
 * public、default 修饰符能够修饰类（外部类）
 */
public class Demo01 {

    // ------------------ field ------------------
    public int a;

    protected int b;

    int c;

    private int d;

    // ------------------ constructor ------------------
    public Demo01() {
        System.out.println("public constructor");
    }

    protected Demo01(int a) {
        System.out.println("protected constructor");
    }

    Demo01(int a, int b) {
        System.out.println("default constructor");
    }

    private Demo01(int a, int b, int c) {
        System.out.println("private constructor");
    }

    // ------------------ method ------------------
    public void method1() {
        System.out.println("public method");
    }

    protected void method2() {
        System.out.println("protected method");
    }

    void method3() {
        System.out.println("default method");
    }

    private void method4() {
        System.out.println("private method");
    }

    // ------------------ inner class ------------------
    public class InnerClassA {
    }

    protected class InnerClassB {
    }

    class InnerClassC {
    }

    private class InnerClassD {
    }
}

/**
 * Description: protected 不能修饰类（外部类）
 */
// protected class ClassB {}

class OuterClassC {
}

/**
 * Description: private 不能修饰类（外部类）
 */
// private class ClassD {}
