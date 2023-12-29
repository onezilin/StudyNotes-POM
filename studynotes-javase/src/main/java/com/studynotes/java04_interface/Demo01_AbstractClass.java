package com.studynotes.java04_interface;

/**
 * Description: 抽象类的声明和实现
 * 有以下特点：
 * * 抽象类包含零个或多个抽象方法；有抽象方法的类必定是抽象类。
 * * 抽象类写法和普通类差不多，但是不能被实例化。
 * * 子类通过 extends 继承抽象类，子类必须实现所有的抽象方法，否则必须也将子类定义为抽象类。
 * * 抽象方法只有声明，没有方法体。
 * * 抽象类中的抽象方法可以被 default、protected、public 修饰，不能为 private。
 */
public class Demo01_AbstractClass {

    public static void main(String[] args) {

        // MyAbstractClass myAbstract = new MyAbstractClass(); // 抽象类不能被实例化
    }

    abstract class MyAbstractClass {

        MyAbstractClass(String name) {
            System.out.println("Basic constructor");
        }

        /**
         * 抽象方法只有声明，没有方法体
         */
        abstract void unimplemented();
    }
}


