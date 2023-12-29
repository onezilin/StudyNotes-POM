package com.studynotes.java12_stream;

import lombok.Data;

import java.util.stream.Stream;

/**
 * Description: peek() 允许查看流中的元素，但是不会对流整体进行修改
 */
public class Demo13_Peek {
    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        demo.setName("first");

        Stream.of(demo)
                .peek(obj -> obj.setName("second"))
                .map(obj -> {
                    System.out.println(obj.getName());
                    obj.setName(obj.getName().toUpperCase());
                    System.out.println(obj.getName());
                    return obj;
                })
                .peek(obj -> obj.setName("forth"))
                .forEach(obj -> System.out.println(obj.getName()));

        System.out.println(demo.getName());
    }

    @Data
    private static class Demo {
        private String name;
    }
}

