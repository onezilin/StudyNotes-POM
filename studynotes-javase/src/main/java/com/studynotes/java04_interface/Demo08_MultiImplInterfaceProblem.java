package com.studynotes.java04_interface;

/**
 * Description: 父接口存在一样的方法签名，但 return 类型不一样时，编译报错。
 */
public class Demo08_MultiImplInterfaceProblem {

    interface MyInterface1 {
        int method(int i);
    }

    interface MyInterface2 {
        void method(int i);
    }

    /**
     * 父接口存在一样的方法签名，不能实现或继承
     */
    // interface Sam extends MyInterface1, MyInterface2 {
    //
    // }
}
