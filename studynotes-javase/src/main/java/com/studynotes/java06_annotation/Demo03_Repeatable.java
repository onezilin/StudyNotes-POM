package com.studynotes.java06_annotation;

/**
 * Description: Java 8 之前，注解的重复使用需要使用容器注解
 */
public class Demo03_Repeatable {

    @interface MyValidates {
        ValidateField[] value();
    }

    /**
     * Description: 用于验证字段值的注解
     */
    @interface ValidateField {
        String fieldName();

        String fieldValue();
    }

    /**
     * Description: 使用容器注解，重复使用 ValidateField 注解
     */
    @MyValidates({
            @ValidateField(fieldName = "name", fieldValue = "zhangsan"),
            @ValidateField(fieldName = "age", fieldValue = "18")
    })
    void test() {
    }
}

