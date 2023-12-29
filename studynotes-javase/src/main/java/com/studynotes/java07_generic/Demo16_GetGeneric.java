package com.studynotes.java07_generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * Description: Class 类对象获取泛型的方式
 */
@Slf4j
public class Demo16_GetGeneric {

    @Test
    public void test() {
        new Parent<>().consume();
        new Son().consume();
    }

    public class Parent<T> {
        Type consume() {
            Class clazz = getClass();
            // getTypeParameters() 获取该 class 对象类型上的泛型参数
            TypeVariable[] genericType = clazz.getTypeParameters();
            log.info("Parent 类：类的class对象 -> {}, class上的泛型 -> {}", clazz, genericType);

            return null;
        }
    }

    public class Son extends Parent<List<MyClass>> {
        @Override
        Type consume() {
            // getClass() 获得该类的 class 对象
            Class clazz = getClass();
            // getTypeParameters() 获取该 class 对象类型上的泛型参数
            TypeVariable[] genericType = clazz.getTypeParameters();
            log.info("Son 类：类的class对象 -> {}, class上的泛型 -> {}", clazz, genericType);

            // getSuperclass() 获得该类的父类的 class 对象，不带泛型
            Class superClazz = clazz.getSuperclass();
            TypeVariable[] superGenericType = clazz.getTypeParameters();
            log.info("Parent 类：类的class对象 -> {}, class上的泛型 -> {}", superClazz, superGenericType);

            // getGenericSuperclass() 获得该类带有泛型的父类，带泛型
            Type genericSuperclass = clazz.getGenericSuperclass();
            // 转化为参数化类型，即泛型
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            // 获取泛型类型数组
            Type[] typeArray = parameterizedType.getActualTypeArguments();

            // 转化为 class 对象
            if (typeArray[0] instanceof ParameterizedType) {
                ParameterizedType genericClass = (ParameterizedType) typeArray[0];
                log.info("获取到的泛型实例：{}", genericClass);
                return genericClass;
            } else if (typeArray[0] instanceof Class) {
                Class genericClass = (Class) typeArray[0];
                log.info("获取到的泛型实例：{}", genericClass);
                return genericClass;
            }
            return null;
        }
    }

    class MyClass {
    }
}
