package com.studynotes.java99_other.demo04_string;

/**
 * Description: String 引用的传递
 * <p>
 * 字符串是不可变的，所以在方法中对字符串的操作都是产生一个新的字符串，而不是在原来的字符串上进行操作
 */
public class Demo01_FinalString {

    public static String upcase(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        System.out.println(q); // howdy

        String qq = upcase(q);
        System.out.println(qq); // HOWDY
        System.out.println(q); // howdy
    }
}
