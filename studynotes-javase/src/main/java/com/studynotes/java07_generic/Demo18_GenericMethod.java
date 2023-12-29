package com.studynotes.java07_generic;


import org.junit.jupiter.api.Test;

/**
 * Description: 仿造 builder 模式，调用泛型方法时，才设置泛型
 */
public class Demo18_GenericMethod {

    @Test
    void test() {
        MyTest<String, Object> builder1 = MyTest.newBuilder().build();

        MyTest<Integer, Object> builder2 = MyTest.<String, Object>newBuilder().build();
    }

    static class MyTest<K, V> {

        public static MyTest<Object, Object> newBuilder() {
            return new MyTest<>();
        }

        /**
         * 通过这种方式可以返回任意泛型的 MyTest 对象
         */
        public <K1 extends K, V1 extends V> MyTest<K1, V1> build() {
            @SuppressWarnings("unchecked")
            MyTest<K1, V1> self = (MyTest<K1, V1>) this;
            return self;
        }
    }
}
