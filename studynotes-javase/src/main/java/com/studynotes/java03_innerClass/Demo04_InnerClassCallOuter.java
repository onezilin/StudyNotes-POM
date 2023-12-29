package com.studynotes.java03_innerClass;

/**
 * Description: 当有同名的成员时，内部类的成员优先级更高，内部类可以通过【外部类.this】方式指定外部类对象
 */
public class Demo04_InnerClassCallOuter {

    private int a = 1;

    private void getA() {
        System.out.println(a); // 1
    }

    class InnerClass {

        private int a = 2;

        private void getA() {
            System.out.println(a); // 2
            /**
             * 获取外部类实例
             */
            System.out.println(Demo04_InnerClassCallOuter.this.a); // 1
        }
    }

    public static void main(String[] args) {
        Demo04_InnerClassCallOuter outerClass = new Demo04_InnerClassCallOuter();
        outerClass.getA();
        InnerClass innerClass = outerClass.new InnerClass();
        innerClass.getA();
    }
}
