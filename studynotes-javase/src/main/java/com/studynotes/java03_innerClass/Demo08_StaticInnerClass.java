package com.studynotes.java03_innerClass;

/**
 * Description: 静态内部类
 * * 静态内部类可以访问外部类的所有**静态成员**，包括私有成员。
 * * 外部类可以访问静态内部类的所有**静态成员**，包括私有成员。
 * * 静态内部类中可以定义**普通成员或静态成员**。
 */
public class Demo08_StaticInnerClass {

    private int a;

    private static int b;

    private void method() {
        InnerClass innerClass = new InnerClass();
        innerClass.innerA = 1;
        innerClass.innerMethod();
        System.out.println(InnerClass.innerB);
    }

    static class InnerClass {
        private int innerA;

        private static int innerB;

        private void innerMethod() {
            // System.out.println(a); // 编译报错，静态内部类不能访问外部类的非静态成员
            System.out.println(b);
            // System.out.println(Demo08_StaticInnerClass.this); // 静态内部类中不可以通过外部类的类名.this访问外部类对象
        }
    }
}
