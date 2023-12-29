package com.studynotes.java16_concurrency;

import java.util.concurrent.locks.ReentrantLock;

// reentrantLock测试
public class Demo27_ReentrantLock {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock1.tryLock();
            System.out.println(111);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.tryLock();
            System.out.println(111111);
            lock2.unlock();
            lock1.unlock();
        });

        Thread t2 = new Thread(() -> {
            lock2.tryLock();
            System.out.println(222);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1.tryLock();
            System.out.println(222222);
            lock1.unlock();
            lock2.unlock();
        });


        t1.start();
        try {
            t1.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        t2.start();
    }
}
