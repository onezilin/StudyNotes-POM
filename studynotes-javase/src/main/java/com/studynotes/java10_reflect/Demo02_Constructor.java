package com.studynotes.java10_reflect;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

/**
 * Description: Constructor 表示类的构造方法，可以获取构造方法的参数类型、修饰符等详细信息，也可以通过 Constructor 实例化对象。
 */
public class Demo02_Constructor {
    static class MyClass {
        public MyClass() {
        }

        public MyClass(String name) {
            System.out.println("name = " + name);
        }

        private MyClass(int age) {
            System.out.println("age = " + age);
        }

        protected MyClass(String name, int age) {
            System.out.println("name = " + name + ", age = " + age);
        }

        MyClass(int age, String name) {
            System.out.println("name = " + name + ", age = " + age);
        }
    }

    @Test
    @SneakyThrows
    void test() {
        Class<MyClass> clazz = MyClass.class;

        // 获取所有 public 构造方法
        Constructor<?>[] publicConstructor = clazz.getConstructors();

        // 获取所有构造方法，包括 protected、default、private
        Constructor<?>[] declaredConstructor = clazz.getDeclaredConstructors();

        // 获取指定参数类型的 public 构造方法
        Constructor<?> definedConstructor = clazz.getConstructor(String.class);
        definedConstructor.newInstance("张三");
    }
}
