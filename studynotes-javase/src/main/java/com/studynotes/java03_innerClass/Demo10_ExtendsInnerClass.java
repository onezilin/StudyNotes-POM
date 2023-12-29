package com.studynotes.java03_innerClass;

/**
 * Description: 继承成员内部类
 * 因为内部类的构造器必须连接到指向其外围类对象的引用，
 * 所以在继承内部类的时候，必须传递一个外部类的对象引用，并且在构造器中使用 `outerClass.super()` 的写法才能通过编译。
 */
public class Demo10_ExtendsInnerClass {

    class InnerClass {
        private int innerA;
    }
}

class SubClass1 extends Demo10_ExtendsInnerClass.InnerClass {

    private int subA;

    /**
     * 固定写法
     */
    public SubClass1(Demo10_ExtendsInnerClass outerClass) {
        outerClass.super();
    }
}
