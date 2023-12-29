package com.studynotes.java99_other.demo04_string;

import java.util.stream.Stream;

// 关于toString的错误递归
public class Demo06_ThisString {

    @Override
    public String toString() {
        return " InfiniteRecursion address: " + this.toString() + "\n"; // 这里的this会调用重写的toString方法，会一直递归然后栈溢出
        // return " InfiniteRecursion address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        Stream.generate(Demo06_ThisString::new).limit(10).forEach(System.out::println);
    }
}
