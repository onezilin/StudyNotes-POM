package com.studynotes.java16_concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 读写锁升降级导致的死锁
public class Demo29_ReentrantReadWriteLockDeadLock {
    public static void main(String[] args) {
        //        upgrade();
        //        downgrade();
        readWriteLock();
    }

    // 锁升级导致的死锁
    static void upgrade() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        System.out.println("获取读锁");
        readWriteLock.writeLock().lock();
        System.out.println("获取写锁");
    }

    static void downgrade() {
        ReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.writeLock().lock();
        System.out.println("获取写锁");

        rtLock.readLock().lock();
        System.out.println("获取读锁");
    }

    static void readWriteLock() {
        final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Thread wt = new Thread(() -> {
            readWriteLock.writeLock().lock();
            System.out.println("writeLock");

            readWriteLock.readLock().lock();
            System.out.println("readLock");

            readWriteLock.readLock().unlock();

            try {
                System.out.println("block");
                Thread.sleep(5000);
                readWriteLock.writeLock().unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        wt.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 写锁未被释放，主线程阻塞
        readWriteLock.readLock().lock();
        System.out.println("main thread get writelock");
    }
}
