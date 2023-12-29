package com.studynotes.java06_annotation;

/**
 * Description: 注解的声明和使用
 */
public class Demo01_InitAnnotation {

    @interface MyAnnotation {

        String value();

        String name() default "zhangsan";
    }

    @MyAnnotation(value = "lisi")
    public void test() {
    }
}
