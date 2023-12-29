package com.studynotes.java01_basis.demo06_super;

import org.junit.jupiter.api.Test;

/**
 * Description: Super 指向当前类**超类（离自己最近的一个父类）**实例的指针，可以访问父类**有访问权限的**成员属性、成员方法
 * 有以下特点：
 * * super 只能在类内部使用，表示当前类父类实例。
 * * 不可以在静态方法中使用 super；可以使用 super 调用静态方法、获取静态属性，但是**不推荐**。
 */
public class Demo01_Super {

    static class GrandFather {
        void method() {
            System.out.println("GrandFather method");
        }
    }

    static class Father extends GrandFather {
        void method() {
            System.out.println("Father method");
        }
    }

    static class Son extends Father {

        @Test
        void method() {
            super.method(); // super 可以调用父类(离自己最近的一个父类)的成员变量和成员方法
            System.out.println("Son method");
        }
    }
}
