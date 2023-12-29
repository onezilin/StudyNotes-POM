package com.studynotes.java18_jvm;

// static执行顺序
public class Demo19_StaticOrder {
    public static void main(String[] args) {

    }
}

class TestOrder {
    // 静态变量
    public static TestA1 a = new TestA1();

    // 静态初始化块
    static {
        System.out.println("静态初始化块");
    }

    // 静态变量
    public static TestB1 b = new TestB1();

    public static void main(String[] args) {
        new TestOrder();
    }
}

class TestA1 {
    public TestA1() {
        System.out.println("Demo01_Reactor--A");
    }
}

class TestB1 {
    public TestB1() {
        System.out.println("Demo01_Reactor--B");
    }
}
