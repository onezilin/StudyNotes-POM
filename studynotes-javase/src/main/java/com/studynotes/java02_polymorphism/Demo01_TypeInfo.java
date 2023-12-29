package com.studynotes.java02_polymorphism;

import org.junit.jupiter.api.Test;

/**
 * Description: 多态是指一个接口或类的不同表现形式
 * <p>
 * * 动态类型：在运行期根据实际类型确定方法执行版本（即重写的方法）。
 * * 静态类型：在作为参数传递或能否获取方法、属性时，是以静态类型作为判断依据。
 */
public class Demo01_TypeInfo {

    @Test
    public void test() {
        Father[] array = {new Father(), new Son()};
        for (Father a : array) {
            a.method(); // 动态类型
            System.out.println(a.name); // 静态类型
        }
    }

    static class Father {

        String name = "father";

        void method() {
            System.out.println("Father 的 method1 方法");
        }
    }

    static class Son extends Father {

        String name = "son";

        @Override
        void method() {
            System.out.println("Son 的 method1 方法");
        }
    }
}

