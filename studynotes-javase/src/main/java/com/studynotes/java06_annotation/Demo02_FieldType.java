package com.studynotes.java06_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description: 注解属性的类型只能为：
 * * 八种基本类型
 * * String
 * * Class
 * * enum
 * * Annotation
 * * 以上类型的数组
 * 属性值不能为 null
 */
public class Demo02_FieldType {

    void test(@MyAnnotation int i) {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface MyAnnotation {

        int number() default 0;

        String string() default "";

        Class<MyClass> myclass() default MyClass.class;

        MyEnum myenum() default MyEnum.RED;

        AnotherAnnotation annotation() default @AnotherAnnotation;

        String[] strings() default {};
    }

    class MyClass {
    }

    enum MyEnum {
        RED, GREEN, BLUE;
    }

    @interface AnotherAnnotation {
    }
}

