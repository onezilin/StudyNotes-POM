package com.studynotes.java03_innerClass;

import org.junit.jupiter.api.Test;

/**
 * Description: 只要是类都会编译成 .class 文件，包括内部类，内部类的 Class 对象名称为：【外部类名$内部类名】
 */
public class Demo03_InnerClassName {

    @Test
    void test() {
        InnerClass innerClass = new InnerClass();
        /**
         * 获取内部类的 .class 文件名
         */
        System.out.println(innerClass.getClass().getTypeName()); // ....Demo03_InnerClassName$InnerClass
    }

    class InnerClass {
    }
}
