package com.studynotes.java18_jvm;

// 垃圾回收：引用计数器测试
public class Demo08_RefrenceCount {
    public static void main(String[] args) {
        TestA<TestB> objA = new TestA<>();
        TestB<TestA> objB = new TestB<>();
        objA.t = objB;
        objB.t = objA;
        // 若采用引用计数法，则会产生相互引用的问题，JVM没有采用这种方式，因此这样没异常
        objA = null;
        objB = null;
        System.gc();
    }
}

class TestA<T> {
    T t;
}

class TestB<T> {
    T t;
}
