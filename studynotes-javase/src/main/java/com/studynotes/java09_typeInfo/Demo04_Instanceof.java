package com.studynotes.java09_typeInfo;


import org.junit.jupiter.api.Test;

/**
 * Description: instanceof 关键字可以判断一个对象是否是某个类或子类的实例
 */
public class Demo04_Instanceof {

    class Father {
    }

    class Son extends Father {
    }

    @Test
    void test() {
        Father father = new Father();
        System.out.println(father instanceof Father); // true
        System.out.println(father instanceof Son); // false

        Father son = new Son();
        System.out.println(son instanceof Father); // true
        System.out.println(son instanceof Son); // true

        Son son1 = new Son();
        System.out.println(son1 instanceof Father); // true
    }
}
