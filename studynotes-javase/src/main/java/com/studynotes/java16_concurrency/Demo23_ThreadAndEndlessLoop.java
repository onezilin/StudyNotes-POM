package com.studynotes.java16_concurrency;

// while(true)循环是否一直占用CPU的测试
public class Demo23_ThreadAndEndlessLoop {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {

            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("即使你死循环了，我还是输出了");
        });

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
