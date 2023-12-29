package com.studynotes.java18_jvm;

// 字符串常量池，可以方便在1.6和1.7的环境下运行，查看区别
// intern会在字符串常量池中不存在该字符串字面量时，建立一个引用，指向堆中的String对象
public class Demo07_StringConstPool {

    public static void main(String[] args) {
        before();
    }

    public static void before() {
        String s = new String("1");
        // 在s2之前intern
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        // 结合下面三个例子，看起来只有new String(字符串)的时候才会将字符串字面量加入到字符串常量池中
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);

        char[] myChar = {'1', '1', '1'};
        String s5 = new String(myChar, 0, 2);
        s5.intern();
        String s6 = "111";
        System.out.println(s5 == s6);

        char[] myChar2 = {'1', '1', '1', '1'};
        String s7 = new String(myChar2);
        s7.intern();
        String s8 = "1111";
        System.out.println(s7 == s8);
    }

    public static void after() {
        String s = new String("1");
        String s2 = "1";
        // 在s2之后intern
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }

    public static void test() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString(); // java默认在字符串常量池中
        System.out.println(str2.intern() == str2);
    }
}
