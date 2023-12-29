package com.studynotes.java99_other.demo04_string;

// String和StringBuffer类的性能比较
public class Demo04_StringBuffer {

    public String implicit(String[] fields) {
        String result = "";
        for (String field : fields) {
            result += field; // 每一次进行字符串拼接的时候都会 new StringBuffer(),性能偏低
        }
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (String field : fields) {
            result.append(field);
        }
        return result.toString();
    }
}

