package com.studynotes.java18_jvm;

import java.io.Serializable;

// 静态分派中，重载方法匹配优先级：基本类型向上转型>再装箱>再装箱的父类
public class Demo03_StaticDispatch {
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void sayHello(char... arg) {
        System.out.println("hello char……");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        // 依次将参数类型为 char、int、long、Character、Serializable、Object的方法注释掉，查看控制台
        sayHello('a');
    }
}
