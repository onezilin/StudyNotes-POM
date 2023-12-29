package com.studynotes.java01_basis.demo03_Final;

/**
 * Description: final 可以修饰成员变量、局部变量，表示变量对应值所在的地址不可变，也就是不能修改
 */
public class Demo01_Final {

    final int A = 1;

    final int B;

    Demo01_Final() {
        B = 2;
    }

    void method1() {
        final int i;
        i = 2;
    }

    void method2(final int i) {
        // i = 2; // 不可更改
    }
}


