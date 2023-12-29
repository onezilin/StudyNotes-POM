package com.studynotes.java03_innerClass;

/**
 * Description: 静态内部类的访问
 * 访问静态内部类不用像成员内部类那样——需要先创建外部类对象，而是直接通过外部类创建静态内部类对象
 */
public class Demo09_StaticInnerClassAccess {

    public static void main(String[] args) {
        /**
         * 通过外部类创建静态内部类对象
         */
        InnerClass innerClass2 = new InnerClass();
        innerClass2.innerA = 1; // 仍然在同一个类中，可以访问
        /**
         * 由于静态内部类是静态的，所以可以直接通过类名访问
         */
        System.out.println(InnerClass.innerB);
    }

    private void method() {
        /**
         * 直接创建成员内部类对象
         */
        InnerClass innerClass = new InnerClass();
        innerClass.innerA = 1;
    }

    static void method2() {
        /**
         * 直接创建静态内部类对象
         */
        InnerClass innerClass = new InnerClass(); // 也可以这样写
        innerClass.innerA = 1;
    }

    static class InnerClass {
        private int innerA;

        private static int innerB;
    }
}
