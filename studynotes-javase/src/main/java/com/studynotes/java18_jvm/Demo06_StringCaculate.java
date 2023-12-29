package com.studynotes.java18_jvm;

// 可以通过class文件看出，凡是 "+" 字符操作符，都是使用了new StringBuilder().append(相加数).toString()方式
public class Demo06_StringCaculate {
    public static void main(String[] args) {
        String s = new String("1") + new String("1"); // 在这里new了两个String对象
        s = s + "111"; // 在这里"111"，并没有new String对象，而是直接传入
        System.out.println(s);
    }
}
