package com.studynotes.java05_enum;

import org.junit.jupiter.api.Test;

/**
 * Description: 在 switch 中使用枚举
 */
public class Demo03_Switch {

    @Test
    void test() {
        for (int i = 0; i < 7; i++) {
            System.out.println(color);
            change();
        }
    }

    Signal color = Signal.RED;

    public void change() {
        switch (color) {
            /**
             * case 后的值就不用再写枚举类型的名称了，直接写枚举常量
             */
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    enum Signal {GREEN, YELLOW, RED,}
}

