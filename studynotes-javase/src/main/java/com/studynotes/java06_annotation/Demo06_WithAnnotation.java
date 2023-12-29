package com.studynotes.java06_annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/**
 * Description: 在注解上加注解，并通过反射获取注解信息
 */
public class Demo06_WithAnnotation {

    @Retention(RetentionPolicy.RUNTIME)
    @interface InnerAnnotation {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @InnerAnnotation
    @interface MyAnnotation {
    }


    @MyAnnotation
    public static class Stater {

    }

    @Test
    void test() {
        // 获取类上的注解信息
        Annotation[] annotation1 = Stater.class.getAnnotations();
        Arrays.stream(annotation1).forEach(System.out::println);

        // 获取注解上的注解信息
        Annotation[] annotation2 = MyAnnotation.class.getAnnotations();
        Arrays.stream(annotation2).forEach(System.out::println);
    }
}
