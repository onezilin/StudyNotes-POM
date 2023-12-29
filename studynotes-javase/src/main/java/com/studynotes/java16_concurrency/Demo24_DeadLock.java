package com.studynotes.java16_concurrency;

// 死锁测试
public class Demo24_DeadLock {
    static class SyncTestA {
    }

    static class SyncTestB {
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (SyncTestA.class) {
                System.out.println(111);
                synchronized (SyncTestB.class) {
                    System.out.println(111111);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (SyncTestB.class) {
                System.out.println(222);
                synchronized (SyncTestA.class) {
                    System.out.println(222222);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
