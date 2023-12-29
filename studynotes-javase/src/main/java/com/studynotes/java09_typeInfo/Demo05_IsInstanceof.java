package com.studynotes.java09_typeInfo;

import org.junit.jupiter.api.Test;

/**
 * Description: 可以使用 Class 对象的 isInstanceof() 方法进行判断,和 instanceof 同样的效果
 */
public class Demo05_IsInstanceof {

    class Father {
    }

    class Son extends Father {
    }

    @Test
    void test() {
        Father father = new Father();
        System.out.println(Father.class.isInstance(father)); // true
        System.out.println(Son.class.isInstance(father)); // false

        Father son = new Son();
        System.out.println(Father.class.isInstance(son)); // true
        System.out.println(Son.class.isInstance(son)); // true

        Son son1 = new Son();
        System.out.println(Father.class.isInstance(son1)); // true
    }
}
