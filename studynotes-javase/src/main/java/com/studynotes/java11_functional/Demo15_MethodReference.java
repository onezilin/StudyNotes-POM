package com.studynotes.java11_functional;

/**
 * Description: 类方法引用【类名::静态方法名】作为方法引用
 */
public class Demo15_MethodReference {

    public static void main(String[] args) {
        Interface1 i1 = (a) -> System.out.println(a);

        // 类名::静态方法名
        Interface1 i2 = Demo15_MethodReference::method;
    }

    static void method(int a) {
        System.out.println(a);
    }

    interface Interface1 {
        void method(int a);
    }
}
