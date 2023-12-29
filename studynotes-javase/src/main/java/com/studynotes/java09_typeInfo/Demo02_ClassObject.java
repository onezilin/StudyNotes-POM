package com.studynotes.java09_typeInfo;

import lombok.SneakyThrows;

/**
 * Description: .class 和 forName()的区别
 * * .class是类字面常量,可以获取类对象的引用但是不会引发初始化,
 * * forName()会立刻初始化类
 */
public class Demo02_ClassObject {

    @SneakyThrows
    public static void main(String[] args) {
        Class<Initable1> initable1 = Initable1.class; // 没有触发类的初始化

        Class<Initable2> initable2 = (Class<Initable2>) Class.forName("com.studynotes.java09_typeInfo" +
                ".Demo02_ClassObject$Initable2"); // Initializing Initable2，说明触发了初始化
    }

    static class Initable1 {
        static {
            System.out.println("Initializing Initable1");
        }
    }

    static class Initable2 {
        static {
            System.out.println("Initializing Initable2");
        }
    }
}


