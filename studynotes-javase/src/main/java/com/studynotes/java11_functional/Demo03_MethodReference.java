package com.studynotes.java11_functional;

/**
 * Description: 方法引用方式实现接口
 */
public class Demo03_MethodReference {
    public static void main(String[] args) {
        Demo03_MethodReference demo03 = new Demo03_MethodReference();

        ZeroArgs zeroArgs = demo03::zero;
        OneArgs oneArgs = Demo03_MethodReference::one;
        TwoArgs twoArgs = Demo03_MethodReference::two;
        ThreeArgs threeArgs = Demo03_MethodReference::three;
        FourArgs fourArgs = Demo03_MethodReference::four;

        zeroArgs.call0();
        oneArgs.call1(demo03, 1);
        twoArgs.call2(demo03, 1, 2.0);
        threeArgs.call3(demo03, 1, 2.0, "Three");
        fourArgs.call4(1, 2.0, "Four", "Args");
    }

    void zero() {
    }

    void one(int a) {
    }

    void two(int a, double b) {
    }

    void three(int a, double b, String c) {
    }

    static void four(int a, double b, String c, String d) {
    }

    interface ZeroArgs {
        void call0();
    }

    interface OneArgs {
        void call1(Demo03_MethodReference athis, int a);
    }

    interface TwoArgs {
        void call2(Demo03_MethodReference athis, int a, double b);
    }

    interface ThreeArgs {
        void call3(Demo03_MethodReference athis, int a, double b, String c);
    }

    interface FourArgs {
        void call4(int a, double b, String c, String d);
    }
}
