package com.studynotes.java16_concurrency;

/**
 * Description: 线程优先级的设置只是表示优先执行的概率变大,不会一定优先执行
 */
public class Demo15_Priority {
    public static void main(String[] args) {
        // 创建三个线程
        Thread thread1 = new Thread(new PriorityThread(), "Thread1");
        Thread thread2 = new Thread(new PriorityThread(), "Thread2");
        Thread thread3 = new Thread(new PriorityThread(), "Thread3");
        // 设置优先级
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(8);
        // 开始执行线程
        thread3.start();
        thread2.start();
        thread1.start();
    }
}

/**
 * 优先级
 */
class PriorityThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
