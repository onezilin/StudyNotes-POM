package com.studynotes.java12_stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Description: Stream.builder() 可以构建 Stream.builder 内部类，该内部类调用 add() 可以添加元素，调用 build() 时转换为Stream
 */
public class Demo10_Builder {

    public static class FileToWordsBuilder {
        Stream.Builder<String> builder = Stream.builder();

        public FileToWordsBuilder(String filePath) throws Exception {
            // File转换为Stream
            Files.lines(Paths.get(filePath))
                    .skip(1) // 略过开头的注释行
                    .forEach(line -> {
                        for (String w : line.split("[ .?,]+"))
                            // add存储元素
                            builder.add(w);
                    });
        }

        Stream<String> stream() {
            // Stream.builder转换为Stream
            return builder.build();
        }

        public static void main(String[] args) throws Exception {
            new FileToWordsBuilder("Cheese.dat")
                    .stream()
                    .limit(7)
                    .map(w -> w + " ")
                    .forEach(System.out::print);
        }
    }

}
