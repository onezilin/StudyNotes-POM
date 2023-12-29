package com.studynotes.java03_innerClass;


/**
 * Description: 定义在代码块中的类称为【局部内部类】
 * 定义一个局部内部类，有以下特点：
 * * 1. 定义在代码块中，作用范围也在代码块中，超出方法范围就不能使用
 * * 2. 只能访问方法中定义的【final】类型的局部变量
 * * 3. 局部内部类不能定义静态成员
 * * 4. 局部内部类可以访问外部类的所有成员，包括私有成员、静态成员
 */
public class Demo06_MthodInnerClass {

    private int a;

    private static int b;

    public void hasInnerClass() {
        /**
         * Description: 局部内部类访问代码块中的局部变量，局部变量必须是final类型的
         */
        final int c = 1;

        class InnerClass {

            private int innerA;

            private void innerMethod() {
                /**
                 * Description: 获取外部类的成员变量
                 */
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
            }
        }

        InnerClass innerClass = new InnerClass();
        innerClass.innerMethod();
    }

    public void method() {
        // int a = new InnerClass("").innerA; // 局部内部类只能在定义它的代码块中使用
    }
}
