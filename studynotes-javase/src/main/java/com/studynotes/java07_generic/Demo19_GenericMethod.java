package com.studynotes.java07_generic;

import org.junit.jupiter.api.Test;

/**
 * Description: 调用泛型方法时，设置泛型
 */
public class Demo19_GenericMethod {

    @Test
    void test() {

        MyTestBuilder<String> builder1 = MyTest.builder();
        MyTest<String> myTest1 = builder1.payload("123").build();

        /**
         * 和上面的写法等价，其中必须设置泛型 <String>
         * 因为默认不写的话就相当于 `MyTestBuilder builder = MyTest.builder();`，编译器无法推断出泛型，就认为是 Object
         */
        MyTest<String> myTest2 = MyTest.<String>builder().payload("123").build();
    }

    static class MyTest<T> {

        private T payload;

        public MyTest(T payload) {
            this.payload = payload;
        }

        public static <T> MyTestBuilder<T> builder() {
            return new MyTestBuilder<T>();
            // return new MyTestBuilder<>(); // 其实就相当于这种写法
        }
    }

    static class MyTestBuilder<T> {

        private T payload;

        public MyTestBuilder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

        public MyTest<T> build() {
            return new MyTest<>(payload);
        }
    }
}
