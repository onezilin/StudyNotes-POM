package com.studynotes.java18_jvm;

// 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不会触发初始化
public class Demo22_ConstClassVar {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
        System.out.println(ConstClass2.HELLOWORLD);
    }
}

class ConstClass {
    static {
        HELLOWORLD = new PrintTest();
        System.out.println("ConstClass init！");
    }

    public static final PrintTest HELLOWORLD;
}

class ConstClass2 {
    static {
        System.out.println("ConstClass2 init！");
    }

    public static final PrintTest HELLOWORLD = new PrintTest();
}

class PrintTest {
    static {
        System.out.println("PrintTest init");
    }
}
