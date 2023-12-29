package com.studynotes.java04_interface;

/**
 * Description: 抽象类可以**继承抽象类或普通类**，抽象类或普通类也可以继承抽象类。
 * 子类通过 extends 继承抽象类，子类必须实现所有的抽象方法，否则必须也将子类定义为抽象类。
 */
public class Demo02_ExtendsAbstractClass {

    class ParentClass {
    }

    /**
     * Description: 抽象类可以继承抽象类或普通类
     */
    abstract class AbstarctClass extends ParentClass {

        abstract void method();
    }

    abstract class ChildClass extends AbstarctClass {
    }

    /**
     * 子类必须实现所有的抽象方法
     */
    class ChildClass2 extends AbstarctClass {

        @Override
        void method() {
            System.out.println("method");
        }
    }
}
