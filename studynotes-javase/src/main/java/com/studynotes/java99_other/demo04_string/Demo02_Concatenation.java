package com.studynotes.java99_other.demo04_string;

// += 用在String类型的变量时，由以前的算数运算变成逻辑运算，即对字符串进行连接
public class Demo02_Concatenation {

    public static class Concatenation {
        public static void main(String[] args) {
            String mango = "mango";

            // 这里编译器内部实现是，会创建一个 StringBuffer 对象，再使用 append 方法将几个字符串做拼接，最后再 toString 输出给变量
            String s = "abc" + mango + "def" + 47;
            System.out.println(s);
        }
    }
}


