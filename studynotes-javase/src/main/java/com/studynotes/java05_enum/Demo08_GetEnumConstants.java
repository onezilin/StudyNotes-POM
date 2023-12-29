package com.studynotes.java05_enum;

import org.junit.jupiter.api.Test;

/**
 * Description: 枚举类向上转型为 Enum 时的问题
 */
public class Demo08_GetEnumConstants {
    enum MyEnum {
        A, B, C;
    }

    @Test
    void test() {
        MyEnum[] myEnum = MyEnum.values();

        /**
         * 向上转型为 Enum 后，实例中没有 values() 方法，编译报错
         */
        Enum e = MyEnum.A;
        // e.values();

        /**
         * 通过枚举类 Class 对象的 getEnumConstants() 方法获取枚举常量数组
         */
        for (Enum en : e.getClass().getEnumConstants()) {
            System.out.println(en);
        }
    }
}
