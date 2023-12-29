package com.studynotes.java08_exception;

/**
 * Description: 异常的捕捉顺序要从子类到父类，若是父类到子类则会编译出错
 */
public class Demo09_CatchOrder {
    static class Father extends Exception {
    }

    static class Son extends Father {
    }

    public static void main(String[] args) {

        // 异常捕获时，从子类到父类
        try {
            throw new Father();
        } catch (Son s) {
            System.out.println("Caught Son");
        } catch (Father a) {
            System.out.println("Caught Father");
        }

        try {
            throw new Son();
        } catch (Father a) {
            System.out.println("Caught Annoyance");
        }
    }
}

