package com.studynotes.java04_interface;

/**
 * Description: 接口中可以定义内部类，且默认为 public static
 */
public class Demo10_InterfaceInnerClass {

    interface MyInterface {
        /**
         * 默认且只能被 public static 修饰，可以不用手动添加
         */
        public static class InnerClass {
        }
    }
}
