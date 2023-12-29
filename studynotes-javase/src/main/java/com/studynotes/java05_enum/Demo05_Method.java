package com.studynotes.java05_enum;

/**
 * Description: 枚举类的方法
 */
public class Demo05_Method {

    enum Explore {
        Here, There
    }

    public static void main(String[] args) {
        // values()方法返回一个包含全部枚举常量的数组
        Explore[] values = Explore.values();
        for (Explore value : values) {
            System.out.println(value);
        }

        // valueOf()方法返回指定名称的枚举常量
        Explore.valueOf("Here");

        // ordinal()方法返回枚举常量的下标
        System.out.println(Explore.Here.ordinal());

        // getDeclaringClass()方法返回枚举常量所属的枚举类
        System.out.println(Explore.Here.getDeclaringClass());
    }
}
