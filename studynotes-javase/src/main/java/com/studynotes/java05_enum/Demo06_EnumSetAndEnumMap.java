package com.studynotes.java05_enum;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Description: EnumSet 和 EnumMap 是专门用来操作枚举类的集合
 */
public class Demo06_EnumSetAndEnumMap {

    enum MyEnum {
        A, B, C;
    }

    public static void main(String[] args) {
        EnumSet<MyEnum> enumSet = EnumSet.allOf(MyEnum.class);

        // EnumMap 的键是枚举类型
        EnumMap<MyEnum, String> enumMap = new EnumMap<>(MyEnum.class);
        System.out.println(enumMap.get(MyEnum.A));
    }
}
