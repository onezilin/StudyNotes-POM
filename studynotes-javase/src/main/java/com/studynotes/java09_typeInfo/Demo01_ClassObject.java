package com.studynotes.java09_typeInfo;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * Description: 四种获取类的 Class 对象方式
 */

public class Demo01_ClassObject<T> {

    @Test
    void test2() {
        Class<Integer> i = Demo01_ClassObject.printClass(Integer.class);
    }

    static <T> T printClass(T clazz) {
        return clazz;
    }

    @Test
    @SneakyThrows
    void test() {
        // 1. 通过类名获取
        Class<?> clazz1 = Demo01_ClassObject.class;
        System.out.println(clazz1);

        // 2. 通过对象获取
        Demo01_ClassObject user = new Demo01_ClassObject();
        Class<?> clazz2 = user.getClass();
        System.out.println(clazz2);

        // 3. 通过全类名获取
        Class<?> clazz3 = Class.forName("com.studynotes.java09_typeInfo.Demo01_ClassObject");
        System.out.println(clazz3);

        // 4. 通过类加载器获取
        ClassLoader classLoader = Demo01_ClassObject.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.studynotes.java09_typeInfo.Demo01_ClassObject");
        System.out.println(clazz4);
    }
}
