package com.studynotes.java05_enum;

/**
 * Description: 枚举类内部可以定义 abstract 方法，让枚举常量实现该方法，赋予各自不同的行为
 */
public class Demo07_Abstract {

    enum MyEnum {
        A {
            @Override
            void method() {
                System.out.println("A");
            }

            void method2() {
                System.out.println("A2");
            }
        }, B {
            @Override
            void method() {
                System.out.println("B");
            }
        }, C {
            @Override
            void method() {
                System.out.println("C");
            }
        };

        // 枚举类中可以定义抽象方法，但是枚举常量必须实现该方法
        abstract void method();
    }

    public static void main(String[] args) {
        MyEnum.A.method();
    }
}
