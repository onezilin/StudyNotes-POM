package com.studynotes.java11_functional;

/**
 * Description: 使用 Lambda 方式定义匿名内部类
 */
public class Demo02_Lambda {
    public static void main(String[] args) {
        Interface1 i1 = () -> System.out.println("i1");

        Interface2 i2 = a -> System.out.println("i2 " + a);

        Interface3 i3 = (a, b, c, d) -> System.out.println("i3 " + a + " " + b + " " + c + " " + d);
    }

    interface Interface1 {
        void method();
    }

    interface Interface2 {
        void method(int a);
    }

    interface Interface3 {
        void method(int a, int b, int c, int d);
    }
}
