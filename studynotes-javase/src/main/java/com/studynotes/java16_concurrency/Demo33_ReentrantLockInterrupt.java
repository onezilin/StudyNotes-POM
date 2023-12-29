package com.studynotes.java16_concurrency;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

// reentrantLock的lock和lockInterrupt区别
public class Demo33_ReentrantLockInterrupt {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                doPrint("thread 1 get lock.");
                do123();
                doPrint("thread 1 end.");

            } catch (InterruptedException e) {
                doPrint("thread 1 is interrupted.");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                doPrint("thread 2 get lock.");
                do123();
                doPrint("thread 2 end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                doPrint("thread 2 is interrupted！！！");
            }
        });

        thread1.setName("thread 1");
        thread2.setName("thread 2");
        thread1.start();
        try {
            Thread.sleep(1000);// 等待一会使得thread1会在thread2前面执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1秒后把线程2中断
        thread2.interrupt();
    }

    private static void do123() throws InterruptedException {
        lock.lockInterruptibly();// 等待锁的过程中会立即响应中断
        doPrint(Thread.currentThread().getName() + " is locked.");
        try {
            doPrint(Thread.currentThread().getName() + " doSoming1....");
            Thread.sleep(10000);// 等待几秒方便查看线程的先后顺序
            doPrint(Thread.currentThread().getName() + " doSoming2....");

            doPrint(Thread.currentThread().getName() + " is finished.");
        } finally {
            lock.unlock();
        }
    }

    private static void doPrint(String text) {
        System.out.println((new Date()).toLocaleString() + " : " + text);
    }
}
