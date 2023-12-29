package com.studynotes.java16_concurrency;

/**
 * Description: 线程的简单应用
 */
public class Demo01_ExtendsThread extends Thread {
    private int countDown = 5;
    private int threadNumber;
    private static int threadCount = 0;

    public Demo01_ExtendsThread() {
        threadNumber = ++threadCount;
        System.out.println("Making " + threadNumber);
    }

    // 线程类的创建需要继承Thread类，并重写run()
    public void run() {
        while (true) {
            System.out.println("Thread " + threadNumber + "(" + countDown + ")");
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            // new 一个线程类只会构建对象,start(),会将当前对象绑定一个线程并执行run()
            new Demo01_ExtendsThread().start();
        System.out.println("All Threads Started");
    }
}
