package com.studynotes.java10_reflect;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * Description: Method 表示类的方法，可以获取方法的参数类型、修饰符、返回值等信息，也可以通过 Method 调用方法。
 */
public class Demo04_Method {

    class Father {

        public void publicFatherMethod() {
            System.out.println("publicFatherMethod");
        }

        protected void protectedFatherMethod() {
            System.out.println("protectedFatherMethod");
        }
    }

    class Son extends Father {

        public void publicMethod() {
            System.out.println("publicMethod");
        }

        protected void protectedMethod() {
            System.out.println("protectedMethod");
        }

        void defaultMethod() {
            System.out.println("defaultMethod");
        }

        private void privateMethod() {
            System.out.println("privateMethod");
        }
    }

    @Test
    @SneakyThrows
    void test() {

        Class<Son> clazz = Son.class;

        // 获取所有 public 方法，包含继承方法（注意还有 Object 内的方法）
        Method[] methodArray = clazz.getMethods();

        // 获取所有方法，包含 protected、default、private，但不包含继承方法
        Method[] declaredMethodArray = clazz.getDeclaredMethods();

        // 获取指定名称及参数的 public 方法，包含继承方法
        Method publicMethod = clazz.getMethod("publicMethod");
        Son son = new Son();
        // 调用指定对象的对应方法
        publicMethod.invoke(son);

        // 获取指定名称及参数方法，包含 protected、default、private，但不包含继承方法
        Method declaredMethod = clazz.getDeclaredMethod("privateMethod");
    }
}
