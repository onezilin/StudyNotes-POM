package com.studynotes.java03_innerClass;

/**
 * Description: 访问成员内部类有以下两种情况：
 * * 在外部类中访问成员内部类：
 *   * 在成员方法中访问：先创建成员内部类的实例，然后通过实例访问内部类的成员。
 *   * 在静态成员方法中访问：先创建外部类的实例，然后通过外部类的实例来创建内部类实例，然后通过实例访问内部类的成员。
 * * 在外部类的外部访问成员内部类：先创建外部类的实例，然后通过外部类的实例来创建内部类实例，然后通过实例访问内部类的成员。
 */
public class Demo02_InnerClassAccess {

    public static void main(String[] args) {
        /**
         * 先创建外部类的实例，然后通过实例访问内部类的成员
         */
        Demo01_InnerClass demo01 = new Demo01_InnerClass();
        Demo01_InnerClass.InnerClass innerClass1 = demo01.new InnerClass();
        // innerClass1.innerA = 1; // 不是内部类所在的外部类，无访问权限

        Demo02_InnerClassAccess demo02 = new Demo02_InnerClassAccess();
        InnerClass innerClass2 = demo02.new InnerClass();
        innerClass2.innerA = 1; // 仍然在同一个类中，可以访问
    }

    private void method() {
        /**
         * 创建成员内部类对象
         */
        InnerClass innerClass = new InnerClass();
        innerClass.innerA = 1;
    }

    static void method2() {
        /**
         * 先创建外部类对象，再通过外部类对象创建成员内部类对象
         */
        InnerClass innerClass = new Demo02_InnerClassAccess().new InnerClass();
        innerClass.innerA = 1;
    }

    class InnerClass {
        private int innerA;
    }
}


