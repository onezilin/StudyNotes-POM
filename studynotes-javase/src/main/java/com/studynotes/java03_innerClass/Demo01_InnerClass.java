package com.studynotes.java03_innerClass;

/**
 * Description: 定义在另一个类内部或一个方法内部的类称为【内部类】
 * <p>
 * 定义一个成员内部类，有以下特点：
 * 1. 成员内部类可以访问外部类的所有成员，包括私有成员、静态成员
 * 2. 外部类可以访问成员内部类实例（也就是先创建一个内部类对象才能通过此对象访问）的所有成员，包括私有成员
 * 3. 成员内部类不能定义静态成员
 */
public class Demo01_InnerClass {

    private int a;

    private static int b;

    private void method() {
        InnerClass innerClass = new InnerClass();
        innerClass.innerA = 1;
        innerClass.innerMethod();
    }

    class InnerClass {

        private int innerA;

        // static int innerB; // 编译报错，成员内部类不能定义静态成员

        private void innerMethod() {
            System.out.println(a);
            System.out.println(b);
        }
    }
}
