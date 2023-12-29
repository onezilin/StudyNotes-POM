package com.studynotes.java12_stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: Pattern 的 splitAsStream() 方法可以根据正则表达式将字符串分割为流
 */
public class Demo12_StringToStream {

    private String all;

    public Demo12_StringToStream(String filePath) throws Exception {
        all = Files.lines(Paths.get(filePath))
                .skip(1) // First (comment) line
                .collect(Collectors.joining(" "));
    }

    public Stream<String> stream() {
        // splitAsStream根据正则表达式, 将字符切割后转化为流
        return Pattern.compile("[ .,?]+").splitAsStream(all);
    }

    public static void main(String[] args) throws Exception {
        Demo12_StringToStream fw = new Demo12_StringToStream("Cheese.dat");
        fw.stream()
                .limit(7)
                .map(w -> w + " ")
                .forEach(System.out::print);
        fw.stream()
                .skip(7)
                .limit(2)
                .map(w -> w + " ")
                .forEach(System.out::print);
    }

}
