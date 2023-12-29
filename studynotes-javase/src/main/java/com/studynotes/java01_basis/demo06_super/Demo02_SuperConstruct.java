package com.studynotes.java01_basis.demo06_super;

/**
 * Description: super() 调用父类指定参数的构造方法
 */
public class Demo02_SuperConstruct {

    static class Father {
        Father() {
            System.out.println("Father");
        }

        Father(int i) {
            System.out.println("Father " + i);
        }
    }

    static class Son extends Father {

        Son() {
            /**
             * // 默认情况会调用父类无参构造方法，可以省略书写
             */
            // super();
        }

        Son(int i) {
            super(i); // 调用父类指定参数的构造方法
            System.out.println("Son");
        }
    }
}
