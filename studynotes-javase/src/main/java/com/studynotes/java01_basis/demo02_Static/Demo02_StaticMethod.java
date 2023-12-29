package com.studynotes.java01_basis.demo02_Static;

/**
 * Description: 重写静态方法的行为是没有意义的，因为静态方法是与类，而不是与单个对象相关联的。
 */
public class Demo02_StaticMethod {

    public static void main(String[] args) {
        Father father = new Son();
        // 在编译期间，就确定是调用 Father 的 method1() 方法
        father.method1(); // 父类静态方法1
        father.method2(); // 父类静态方法2

        // 在编译期间，就确定是调用 Son 的 method1() 方法
        Son son = new Son();
        son.method1(); // 子类静态方法
        son.method2(); // 父类静态方法2
    }

    static class Father {

        static void method1() {
            System.out.println("父类静态方法1");
        }

        static void method2() {
            System.out.println("父类静态方法2");
        }
    }

    static class Son extends Father {

        // @Override // 不能使用 @Override 表明重写，只能在使用时覆盖
        static void method1() {
            System.out.println("子类静态方法");
        }
    }
}
