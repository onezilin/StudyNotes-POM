package com.studynotes.java18_jvm;

// 类的加载顺序和类变量的提前赋值
public class Demo20_ClassLoaderOrder {
    public static void main(String[] args) {

    }
}

class A {
    static {
        System.out.println("A");
    }

    void test() {
        System.out.println("testA");
    }
}

class B extends A {
    static {
        System.out.println("B");
    }

    void test() {
        System.out.println("testB");
    }

    void testExtension() {
        System.out.println("testExtension");
    }

    public static void main(String[] args) {
        A a = new B();
        a.test();
    }
}

class C extends A {
    static {
        System.out.println("C");
    }
}

// 接口的静态属性初始化
interface D {
    A a = new A();
    A c = new C();
}

class E implements D {

    public static void main(String[] args) {
        System.out.println(D.a);
        float f = 10;
        double d = 10;

    }
}
