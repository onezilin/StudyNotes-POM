package com.studynotes.java11_functional;

/**
 * Description: 传统方式实现接口与 Lambda、方法引用方式对比
 */
public class Demo01_Compare {

    interface ClassA {
        void getString();
    }

    ClassA class2 = () -> System.out.println("hello");

    public static void main(String[] args) {
        // 对比不同方式实现 Father 接口
        Father[] array = {
                new Father() {
                    @Override
                    public String method1(String msg) {
                        return "Father " + msg;
                    }
                },
                new Son(),
                msg -> "Lambda " + msg,
                Other::method2
        };

        for (Father father : array) {
            System.out.println(father.method1("msg"));
        }
    }

    interface Father {
        String method1(String msg);
    }

    static class Son implements Father {
        public String method1(String msg) {
            return "Son " + msg;
        }
    }

    static class Other {
        static String method2(String msg) {
            return "Other " + msg;
        }
    }
}
