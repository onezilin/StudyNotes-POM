package com.studynotes.java01_basis.demo05_this;

/**
 * Description: this 是指向当前类实例的指针，可以用来访问当前类的成员变量和成员方法
 * 有以下特点：
 * * this 只能在实例方法中使用，静态方法中不能使用 this
 * * this() 只能在构造方法中使用，且必须放在构造方法的第一行
 * * this() 和 super() 不能同时使用
 * * 静态方法中不能使用 this
 */
public class Demo01_This {

    private int i;

    private int j;

    private int k;

    void method1(int k) {
        this.i = 1;

        /**
         * 也可以省略 this，等价于 this.j = 2;
         */
        j = 2;

        /**
         * 当有局部变量和成员变量同名时，需要用 this 来区分
         */
        this.k = k;
        System.out.println("method1");
    }

    void method2() {
        method1(3);
        this.method3(); //
        System.out.println("method2");
    }

    static void method3() {
        // this.i = 1; // 静态方法中不能使用 this
    }
}
