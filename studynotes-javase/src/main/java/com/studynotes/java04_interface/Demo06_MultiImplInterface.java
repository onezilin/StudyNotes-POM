package com.studynotes.java04_interface;

import org.junit.jupiter.api.Test;

/**
 * Description: 不同于类的单继承，接口可以多继承，一个类可以实现多个接口，一个接口也可以继承多个接口。
 */
public class Demo06_MultiImplInterface {

    @Test
    void test() {
        MI mi = new MI();
        mi.first();
        mi.second();
    }

    interface One {
        default void first() {
            System.out.println("first");
        }
    }

    interface Two {
        default void second() {
            System.out.println("second");
        }
    }

    /**
     * 一个接口可以继承多个接口
     */
    interface All extends One, Two {
    }

    /**
     * 一个类可以实现多个接口
     */
    class MI implements One, Two {
    }
}





