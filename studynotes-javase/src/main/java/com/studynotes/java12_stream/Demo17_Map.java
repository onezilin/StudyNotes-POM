package com.studynotes.java12_stream;

import java.util.stream.Stream;

/**
 * Description: 使用 Function 函数，将输入转换为自己需要的函数类型
 */
public class Demo17_Map {
    public static void main(String[] args) {
        Stream.of(1, 5, 7, 9, 11, 13)
                .map(Demo::new)
                .forEach(System.out::println);
    }

    static class Demo {
        final int n;

        Demo(int n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return "Numbered(" + n + ")";
        }
    }
}
