package com.studynotes.java18_jvm;

/**
 * 非主动使用类字段演示,查看类的初始化条件
 **/
public class Demo21_ClassInitCondition {
    public static void main(String[] args) {
        //        没有符合五种初始化类的条件，不会初始化SubClass类
        System.out.println(SubClass.value);
        //        数组不会触发Class初始化
        SubClass[] sca = new SubClass[10];
    }
}

class SuperClass {
    static {
        System.out.println("SuperClass init！");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init！");
    }
}
