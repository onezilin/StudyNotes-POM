package com.studynotes.java11_functional;

/**
 * Description: 成员方法引用【this::非静态方法名】或【对象::非静态方法名】作为方法引用
 */
public class Demo18_MethodReference {

    public static void main(String[] args) {
        Interface1 i1 = (a) -> System.out.println(a);

        Demo18_MethodReference demo = new Demo18_MethodReference();
        // 类名::非静态方法名
        Interface1 i2 = demo::method;
    }

    void method(int a) {
        System.out.println(a);
    }

    interface Interface1 {
        void method(int a);
    }
}
