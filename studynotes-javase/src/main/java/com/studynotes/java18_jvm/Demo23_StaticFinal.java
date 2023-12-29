package com.studynotes.java18_jvm;

/**
 * Description: 测试 static final 和 static 指向的对象的初始化时机
 * 结论：和 final 无关，final 只是表示此变量不可变
 */
public class Demo23_StaticFinal {

    public static final Demo01 demo01 = new Demo01();

    public static Demo02 demo02 = new Demo02();

    public static class Demo01 {

        static {
            System.out.println("Demo01 类初始化");
        }

        public Demo01() {
            System.out.println("Demo01 对象初始化");
        }
    }

    public static class Demo02 {

        static {
            System.out.println("Demo02 类初始化");
        }

        public Demo02() {
            System.out.println("Demo02 对象初始化");
        }
    }

    public static void main(String[] args) {
        System.out.println("初始化");
    }
}
