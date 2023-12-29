package com.studynotes.java16_concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// 读写锁测试
public class Demo28_ReentrantReadWriteLock {
    private static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            READ_WRITE_LOCK.writeLock().lock();
            System.out.println("线程1获取写锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            READ_WRITE_LOCK.writeLock().unlock();
            System.out.println("线程1释放写锁");
        });

        Thread t2 = new Thread(() -> {
            READ_WRITE_LOCK.readLock().lock();
            System.out.println("线程2获取读锁");
            READ_WRITE_LOCK.readLock().unlock();
            System.out.println("线程2释放读锁");
        });

        t1.start();
        t2.start();
    }
}
