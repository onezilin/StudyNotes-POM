package com.studynotes.java06_annotation;

import java.lang.annotation.Repeatable;

/**
 * Description: Java 8 之后，注解的重复使用可以使用 @Repeatable 注解
 */
public class Demo04_Repeatable {

    @interface MyValidates {
        // 必须要有一个 value 方法，且值为 @Repeatable 所在的注解数组
        ValidateField[] value();
    }

    /**
     * Description: 用于验证字段值的注解
     */
    @Repeatable(MyValidates.class) // 容器注解的 Class 对象
    @interface ValidateField {
        String fieldName();

        String fieldValue();
    }

    /**
     * Description: 直接重复使用注解
     */
    @ValidateField(fieldName = "name", fieldValue = "zhangsan")
    @ValidateField(fieldName = "age", fieldValue = "18")
    void test() {
    }
}
