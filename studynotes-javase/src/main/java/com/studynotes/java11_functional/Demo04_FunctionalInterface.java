package com.studynotes.java11_functional;

/**
 * Description: @FuntionalInterface 注解定义函数式接口
 */
public class Demo04_FunctionalInterface {

    public String goodbye(String arg) {
        return "Goodbye, " + arg;
    }

    public static void main(String[] args) {
        Demo04_FunctionalInterface fa = new Demo04_FunctionalInterface();
        Functional f = fa::goodbye;
        FunctionalNoAnn fna = fa::goodbye;

        Functional fl = a -> "Goodbye, " + a;
        FunctionalNoAnn fnal = a -> "Goodbye, " + a;
    }

    @FunctionalInterface
    interface Functional {
        /**
         * Description: 函数式接口中只能有一个抽象方法
         */
        String goodbye(String arg);
    }

    @FunctionalInterface
    interface FunctionalNoAnn {
        String goodbye(String arg);
    }
}
