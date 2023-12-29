package com.studynotes.java99_other.demo04_string;

/**
 * Description: String 内部的 format 方法，方法内部也是创建了Formatter对象
 */
public class Demo10_Format {

    public static void main(String[] args) {
        System.out.println(String.format("(t%d, q%d) %s", 3, 7, "Write failed"));
    }
}
