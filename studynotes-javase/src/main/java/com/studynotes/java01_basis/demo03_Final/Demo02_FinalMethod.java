package com.studynotes.java01_basis.demo03_Final;

/**
 * Description: final 修饰方法时，子类不能覆盖，也就是子类中不能有一样的方法签名
 */
public class Demo02_FinalMethod {

    static class Father {
        final void method() {
            System.out.println("Father 的 final 方法");
        }
    }

    static class Son extends Father {

        // 不能覆盖，也就是不能有一样的方法签名
        // final void method() {
        //     System.out.println("Son 的 final 方法");
        // }
    }
}



