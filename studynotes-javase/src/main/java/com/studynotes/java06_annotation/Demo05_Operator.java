package com.studynotes.java06_annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Description: 注解处理器通过反射获取注解的值，然后进行处理
 */
@Slf4j
public class Demo05_Operator {

    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotation {

        String value();
    }

    @MyAnnotation("lisi")
    void method() {
    }

    @Test
    void test() {
        for (Method m : Demo05_Operator.class.getDeclaredMethods()) {
            // 获取方法上的注解信息
            MyAnnotation annotation = m.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                log.info("获取方法 {} 上 MyAnnotation 注解的 value 值：{}", m.getName(), annotation.value());
            }
        }
    }
}

