package com.studynotes.java11_functional;

/**
 * Description: 构造器引用【类名::new】 用于生成对象
 */
public class Demo17_MethodReference {

    Demo17_MethodReference(int a) {
        System.out.println(a);
    }

    public static void main(String[] args) {
        Interface1 i1 = (a) -> new Demo17_MethodReference(a);

        Interface1 i2 = Demo17_MethodReference::new;
    }

    interface Interface1 {
        Demo17_MethodReference method(int a);
    }
}
