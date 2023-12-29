package com.studynotes.java18_jvm;

import java.util.List;

// 在重载时的问题
public class Demo14_OverrideProblem {
    //    泛型擦除时的问题
    //    public static String method (List<String> list){
    //        System.out.println("invoke method(List<String＞list)");
    //        return"";
    //    }

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer＞list)");
        return 1;
    }


    //    返回值不是方法特征
    //    static String test() {
    //        return "";
    //    }

    static void test() {

    }
}
