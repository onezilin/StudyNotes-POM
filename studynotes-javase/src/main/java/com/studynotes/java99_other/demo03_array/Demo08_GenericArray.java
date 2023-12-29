package com.studynotes.java99_other.demo03_array;

// 泛型擦除功能，会将泛型的类型擦除，对于编译器来说是一个未知的类型
// 不可以实例化泛型类，可以用类型转换的思想间接实现泛型数组
public class Demo08_GenericArray<T> {
    T[] array; // OK

    @SuppressWarnings("unchecked")
    public Demo08_GenericArray(int size) {
        // 泛型擦除
        //		 array = new T[size];
        array = (T[]) new Object[size];
    }
}

