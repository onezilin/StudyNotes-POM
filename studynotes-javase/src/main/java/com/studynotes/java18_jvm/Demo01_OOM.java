package com.studynotes.java18_jvm;

import java.util.ArrayList;
import java.util.List;

// OOM测试
public class Demo01_OOM {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        int i = 0;
        while (true) {
            test.add(String.valueOf(i++));
        }
    }
}
