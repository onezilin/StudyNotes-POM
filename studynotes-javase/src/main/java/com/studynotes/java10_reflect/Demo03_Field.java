package com.studynotes.java10_reflect;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * Description: Field 表示类的字段，可以获取字段的参数类型、修饰符等信息，也可以通过 Field 设置值。
 */
public class Demo03_Field {

    static class Father {

        public String publicFatherField;

        protected String protectedFatherField;
    }

    static class Son extends Father {

        public String publicField;

        protected String protectedField;

        String defaultField;

        private String privateField;
    }

    @Test
    @SneakyThrows
    void test() {
        Class<Son> clazz = Son.class;

        // 获取所有 public 字段，包含继承字段
        Field[] fieldArray = clazz.getFields();

        // 获取所有字段，包含 protected、default、private，但不包含继承字段
        Field[] declaredFieldArray = clazz.getDeclaredFields();

        // 获取指定名称的 public 字段，包含继承字段
        Field publicField = clazz.getField("publicField");

        Son son = new Son();
        // 为指定对象的对应字段设置值
        publicField.set(son, "反射设置 public 字段");
        System.out.println("son.publicField = " + son.publicField);

        // 获取指定名称的字段，包含 protected、default、private，但不包含继承字段
        Field declaredField = clazz.getDeclaredField("privateField");
    }
}
