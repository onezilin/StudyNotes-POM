package com.studynotes.java11_functional;

/**
 * Description: 类方法引用【类名::非静态方法名】作为方法引用
 */
public class Demo16_MethodReference {

    public static void main(String[] args) {
        Interface1 i1 = (demo, a) -> System.out.println(demo.toString() + a);

        Interface1 i2 = Demo16_MethodReference::method;
    }

    void method(int a) {
        System.out.println(a);
    }

    interface Interface1 {
        void method(Demo16_MethodReference demo, int a);
    }
}
