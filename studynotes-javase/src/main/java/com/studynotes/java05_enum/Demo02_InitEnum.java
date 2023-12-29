package com.studynotes.java05_enum;

/**
 * Description: 枚举类的初始化，通过枚举常量的构造函数进行初始化
 */
public class Demo02_InitEnum {

    enum MyEnum {
        RED, GREEN("绿色"), BLUE("蓝色", "#0000ff");

        private String name;

        private String rgb;

        MyEnum() {
        }

        MyEnum(String name) {
            this.name = name;
        }

        MyEnum(String name, String rgb) {
            this.name = name;
            this.rgb = rgb;
        }
    }
}
