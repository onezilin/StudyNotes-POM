package com.studynotes.java07_generic;

/**
 * Description: 使用泛型的 Class 对象，可以接收任何类型的数据
 */
public class Demo02_Generic<T> {

    private T a;

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }
}
