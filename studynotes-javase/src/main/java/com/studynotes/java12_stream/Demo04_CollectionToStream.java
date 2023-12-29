package com.studynotes.java12_stream;

import java.util.*;
import java.util.stream.Stream;

/**
 * Description: 集合了中提供 stream() 方法，可以将集合转换为 Stream 流
 */
public class Demo04_CollectionToStream {
    public static void main(String[] args) {
        String[] arr = "It's a wonderful day for pie!".split(" ");
        Stream.of(arr).forEach(System.out::println);



        List<Bubble> bubbles = Arrays.asList(new Bubble(1), new Bubble(2), new Bubble(3));
        bubbles.stream().mapToInt(b -> b.i).sum();

        Set<String> w = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        w.stream().map(x -> x + " ").forEach(System.out::print);
        System.out.println();

        Map<String, Double> m = new HashMap<>();
        m.put("pi", 3.14159);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream().map(e -> e.getKey() + " " + e.getValue()).forEach(System.out::println);
    }
}
