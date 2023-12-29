package com.studynotes.java03_innerClass;

/**
 * Description: 内部类可以继承实现其他类（包括外部类）
 */
public class Demo05_InnerClassExtends {

    public static void main(String[] args) {
        Demo05_InnerClassExtends outerClass = new Demo05_InnerClassExtends();
        /**
         * Description: 获取内部类实例
         */
        MyInterface myInterface = outerClass.selector();
    }

    public MyInterface selector() {
        return new InnerClass();
    }

    /**
     * Description: 实现其他类
     */
    private class InnerClass extends Demo05_InnerClassExtends implements MyInterface {
    }

}

interface MyInterface {
}

