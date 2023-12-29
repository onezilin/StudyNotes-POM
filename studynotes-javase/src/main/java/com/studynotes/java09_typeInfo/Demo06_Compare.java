package com.studynotes.java09_typeInfo;

import org.junit.jupiter.api.Test;

/**
 * Description: instanceof、isInstanceof 和 equals 、 == 比较
 * * instanceof和isInstanceof 是用来确定对象的类型是否是某个类或其基类
 * * equals 重写后用来比较值是否相同
 * * == 用来比较值的地址是否相同
 */
public class Demo06_Compare {

    class Father {
    }

    class Son extends Father {
    }

    static void test(Object x) {
        System.out.println(
                "Testing x of type " + x.getClass());
        System.out.println(
                "x instanceof Father " + (x instanceof Father));
        System.out.println(
                "x instanceof Son " + (x instanceof Son));
        System.out.println(
                "Father.isInstance(x) " + Father.class.isInstance(x));
        System.out.println(
                "Son.isInstance(x) " + Son.class.isInstance(x));
        System.out.println(
                "x.getClass() == Father.class " + (x.getClass() == Father.class));
        System.out.println(
                "x.getClass() == Son.class " + (x.getClass() == Son.class));
        System.out.println(
                "x.getClass().equals(Father.class)) " + (x.getClass().equals(Father.class)));
        System.out.println(
                "x.getClass().equals(Son.class)) " + (x.getClass().equals(Son.class)));
    }

    @Test
    void test() {
        test(new Father());
        test(new Son());
    }
}



