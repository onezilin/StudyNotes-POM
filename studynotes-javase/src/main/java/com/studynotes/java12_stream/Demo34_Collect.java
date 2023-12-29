package com.studynotes.java12_stream;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo34_Collect {
    public static void main(String[] args) throws Exception {
        ArrayList<String> words =
                FileToWords
                        .stream("china english french china1 english1 french1 china2 english2 french2 cn c c " +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "china english french china1 english1 french1 china2 english2 french2 cn c c" +
                                "")
                        .parallel()
                        .collect(ArrayList::new,
                                ArrayList::add,
                                (list1, list2) -> {
                                    list1.addAll(list2);
                                });
        System.out.println(words.hashCode());

        // 第 1 个参数是每个元素之间的分隔符
        // 第 2 个参数是生成的字符串的前缀，
        // 第 3 个参数是生成的字符串的后缀
        String reult = words.stream().collect(Collectors.joining("、", "【", "】"));
        System.out.println(reult);
    }

    static class FileToWords {
        public static Stream<String> stream(String string) throws Exception {
            return Pattern.compile(" ").splitAsStream(string)
                    .map(String::trim)
                    .filter(s -> s.length() > 2);
        }
    }

}

