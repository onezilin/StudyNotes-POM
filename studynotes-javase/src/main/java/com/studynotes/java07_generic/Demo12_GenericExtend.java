package com.studynotes.java07_generic;

/**
 * Description: 子类可以在继承父类的泛型后，再加以边界
 */
public class Demo12_GenericExtend {

    static class Parent<T> {
    }

    static class Son<T extends Number> extends Parent<T> {
    }
}
