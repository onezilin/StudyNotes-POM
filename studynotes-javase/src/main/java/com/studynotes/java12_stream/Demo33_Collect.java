package com.studynotes.java12_stream;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: collect() 将 Stream 中元素收集起来，转换为集合、String、进行分组、sum等
 */
public class Demo33_Collect {
    public static void main(String[] args) throws Exception {
        Stream<String> common = Pattern.compile(" ").splitAsStream("china english french cn c")
                .map(String::trim)
                .filter(s -> s.length() > 2)
                .limit(100);
        Set<String> words2 = common.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(words2);

        // stream只能被遍历一次
        //        List<String> words3 = common.collect(Collectors.toCollection(ArrayList::new));
        //        System.out.println(words3);
    }
}
