package com.studynotes.java16_concurrency;

import java.util.concurrent.CountDownLatch;

// CountDownLatch测试
public class Demo34_CountDownLatch {

    static final int COUNT = 5;

    static final CountDownLatch LATCH = new CountDownLatch(COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < COUNT * 2; i++) {
            new Thread(() -> {
                System.out.println("线程启动，等待3秒");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 调用一次，COUNT 减 1
                LATCH.countDown();
                System.out.println("线程执行完毕");
            }).start();
        }

        System.out.println("主线程等待");
        // COUNT 为0时，才会唤醒被阻塞的线程
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续执行");
    }
}
