package com.studynotes.java04_interface;

/**
 * Description: 接口的声明和实现
 * 有以下特点：
 * * 接口中的所有方法都是抽象方法，包含零个或多个抽象方法。
 * * 接口中的属性**默认且只能**被 `public static final` 修饰，可以不用手动添加。
 * * 接口中的抽象方法**默认且只能**被 `public abstract` 修饰，可以不用手动添加。
 */
public class Demo03_Interface {

    interface MyInterface {

        /**
         * 默认被 public static final 修饰，可以不用手动添加
         */
        int MY_NUMBER = 1;

        /**
         * 默认被 public abstract 修饰，可以不用手动添加
         */
        void method();
    }
}
