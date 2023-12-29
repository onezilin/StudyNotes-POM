package com.studynotes.java99_other.demo04_string;

// String 的初始化
public class Demo03_Init {

    public static void main(String[] args) {
        // str1 str2的初始化,是在 String 池新建一个String对象,然后后面相同值的字符按都会指向这段内存地址
        String str1 = "ABC";
        String str2 = "ABC";

        // str3 只在堆上new 一个字符串,如果String池中没有这个字符串就还会new一个String对象
        String str3 = new String("ABC");
        System.out.println(str1 == str2); // true
        System.out.println(str1 == str3); // false
    }
}
