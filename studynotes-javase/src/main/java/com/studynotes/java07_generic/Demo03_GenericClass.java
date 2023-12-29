package com.studynotes.java07_generic;

/**
 * Description: 泛型类
 */
public class Demo03_GenericClass {

    interface Myinterface<A> {

    }

    /**
     * 1、子类实现泛型类时，可以不指定父类泛型的类型，此时默认为Object类型
     */
    static class MyClass1 implements Myinterface {
    }

    /**
     * 2、子类实现泛型类时，可以指定父类泛型的类型，表示父类只能接收String类型
     */
    static class MyClass2 implements Myinterface<String> {
    }

    /**
     * 3、子类实现泛型类时，可以不指定父类泛型的类型，继续作为泛型类
     * 其中子泛型类的泛型必须要包括父类所有泛型，表示子类和父类的泛型一样
     */
    static class MyClass3<A> implements Myinterface<A> {
    }

    /**
     * 4、子类实现泛型类时，可以不指定父类泛型的类型，并且可以增加新的泛型
     */
    static class MyClass4<A, B> implements Myinterface<A> {}

    /**
     * 5、子类实现泛型类时，可以指定父类泛型的类型，并且可以增加新的泛型
     */
    static class MyClass5<B, C> implements Myinterface<String> {}

}
