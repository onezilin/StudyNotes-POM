package com.studynotes.java16_concurrency;

import java.util.concurrent.Semaphore;


// Semaphore测试
public class Demo36_Semaphore {
    private static final int COUNT = 5;

    private static final Semaphore SEMAPHORE = new Semaphore(COUNT);


    public static void main(String[] args) {

        for (int i = 0; i < COUNT * 2; i++) {
            new Thread(() -> {
                try {
                    // 需要获取信号量才能执行下一步，消耗一个信号量
                    SEMAPHORE.acquire();
                    System.out.println(Thread.currentThread().getName() + "线程启动");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "线程执行完毕");
                    // 释放信号量
                    SEMAPHORE.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
