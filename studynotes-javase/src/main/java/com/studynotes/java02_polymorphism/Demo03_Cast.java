package com.studynotes.java02_polymorphism;

import org.junit.jupiter.api.Test;

/**
 * Description: 多态向下转型
 */
public class Demo03_Cast {

    @Test
    public void test01() {
        Father f = new Son();
        f.method(); // Son method

        Son s = (Son) f; // 向下转型，将 Father 强制转换为 Son 类型
        s.play(); // Son play
    }

    static class Father {

        public void method() {
            System.out.println("Father method");
        }
    }

    static class Son extends Father {
        @Override
        public void method() {
            System.out.println("Son method");
        }

        public void play() {
            System.out.println("Son play");
        }
    }
}
