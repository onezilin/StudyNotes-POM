package com.studynotes.java05_enum;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * Description: 使用反射分析枚举类，查看枚举类的父类、接口、方法等信息
 */
public class Demo04_EnumInfo {
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("_____ Analyzing " + enumClass + " _____");
        System.out.println("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces()) System.out.println(t);
        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();
        for (Method m : enumClass.getMethods()) methods.add(m.getName());
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
        System.out.print("Explore.removeAll(Enum): "); // 只剩下 Explore 中的 values 方法
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);
    }

    enum Explore {HERE, THERE}
}

