package com.studynotes.java03_innerClass;

/**
 * Description: 继承静态内部类
 */
public class Demo11_ExtendsInnerClass {

    static class InnerClass {
        private int innerA;
    }
}

/**
 * Description:
 */
class SubClass2 extends Demo11_ExtendsInnerClass.InnerClass {

    private int subA;
}
