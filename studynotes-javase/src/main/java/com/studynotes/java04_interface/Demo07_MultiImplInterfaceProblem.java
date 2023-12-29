package com.studynotes.java04_interface;

/**
 * Description: 父接口存在一样的方法签名，且 return 类型一样时，一切正常。
 */
public class Demo07_MultiImplInterfaceProblem {

    interface MyInterface1 {
        int method(int i);
    }

    interface MyInterface2 {
        int method(int i);
    }

    interface Sam extends MyInterface1, MyInterface2 {

    }
}


