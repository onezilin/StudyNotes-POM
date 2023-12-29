package com.studynotes.java04_interface;

/**
 * Description: 父接口存在一样的 default 方法签名时，需要重写，否则编译报错
 */
public class Demo09_MultiImplInterfaceProblem {

    interface Bob1 {
        default void bob() {
            System.out.println("Bob1::bob");
        }
    }

    interface Bob2 {
        default void bob() {
            System.out.println("Bob2::bob");
        }
    }

    /**
     * 父接口存在一样的 default 方法名时，需要重写，否则编译报错
     */
    class Bob implements Bob1, Bob2 {
        @Override
        public void bob() {
            /**
             * 通过 `接口名.super.方法名` 的方式调用指定父接口的方法
             */
            Bob1.super.bob();
            Bob2.super.bob();
        }
    }
}
