package com.studynotes.java03_innerClass;

/**
 * Description: 可以看出内部类其实就像一个成员方法，可以被子类中的内部类覆盖
 */
public class Demo12_CoverInnerClass {

    public static void main(String[] args) {
        OuterClass2 outerClass2 = new OuterClass2();

        MyInterface innerClass1 = outerClass2.new InnerClass1();
        System.out.println(innerClass1.getClass().getName()); // OuterClass1$InnerClass1

        MyInterface innerClass2 = outerClass2.new InnerClass2();
        System.out.println(innerClass2.getClass().getName()); // OuterClass2$InnerClass2
    }

    interface MyInterface {}

    static class OuterClass1 {

        class InnerClass1 implements MyInterface {

        }

        class InnerClass2 implements MyInterface {

        }
    }

    static class OuterClass2 extends OuterClass1 {

        class InnerClass2 implements MyInterface {

        }
    }
}
