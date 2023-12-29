package com.studynotes.java01_basis.demo02_Static;

/**
 * Description: static 关键字可以修饰成员变量、成员方法、代码块、内部类
 */
public class Demo01_Static {

    static int a = 1;

    static {
        System.out.println("静态代码块");
        // objectMethod(); // 静态代码块中不能调用非静态方法
    }

    static void method() {
        System.out.println("静态方法");
        // objectMethod(); // 静态方法中不能调用非静态方法
    }

    static class InnerClass {
        private int innerA;
    }

    void objectMethod() {
    }
}
