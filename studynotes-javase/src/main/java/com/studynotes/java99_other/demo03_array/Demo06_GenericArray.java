package com.studynotes.java99_other.demo03_array;

// 但是不能实例化泛型数组对象，因为会编译阶段会泛型擦除
// 实用性不高，一般都是用集合泛型替代
public class Demo06_GenericArray {
    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5};
        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Integer[] ints2 = new ClassParameter<Integer>().f(ints);
        Double[] doubles2 = new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);
    }
}

// 但是可以用泛型类，实现泛型数组，可以作为参数和返回值在方法中使用
class ClassParameter<T> {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}

class MethodParameter {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}
