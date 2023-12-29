package com.studynotes.java03_innerClass;

/**
 * Description: 匿名内部类
 */
public class Demo07_AnoymousInnerClass {

    public void test() {
        /**
         * 有参构造方法的匿名内部类
         */
        MyClass myClass = new MyClass("哈哈") {
            @Override
            public void method() {
                System.out.println("匿名内部类的方法");
            }

            public void method2() {
                System.out.println("匿名内部类的方法2");
            }
        };

        /**
         * 虽然匿名内部类中自己定义了 method2() 方法，但是由于匿名内部类类型为 MyClass，所以不能调用 method2() 方法
         */
        // myClass.method2(); // 编译报错

        MyAbstractClass myAbstractClass = new MyAbstractClass() {
        };

        MyInterface myInterface = new MyInterface() {
        };
    }

    class MyClass {

        public MyClass(String name) {
            System.out.println("MyClass(String name)");
        }

        public void method() {
            System.out.println("method");
        }
    }

    abstract class MyAbstractClass {
    }

    interface MyInterface {
    }
}

