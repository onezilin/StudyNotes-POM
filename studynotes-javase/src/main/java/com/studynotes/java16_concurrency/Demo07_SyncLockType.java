package com.studynotes.java16_concurrency;

/**
 * Description: synchronized 对象锁和类锁
 */
public class Demo07_SyncLockType {
    public static void main(String[] args) {
        // 创建一个对象实例
        SynchronizedTest synchronizedTest = new SynchronizedTest();

        // 1.对象锁,两个线程同时竞争同一对象锁,其中一个线程拿到对象锁后,其他线程不能执行该对象中的其他同步方法
        /**
         * 线程1，执行该实例的printA方法
         */
        new Thread(synchronizedTest::printA).start();

        /**
         * 线程2，执行该实例的printB方法
         */
        new Thread(synchronizedTest::printB).start();

        // 2.对象锁,两个线程拥有两个不同的对象锁,所以不会存在竞争的问题
        /**
         * 线程1，执行该实例的printA方法
         */
        new Thread(() -> {
            // 创建一个对象实例
            SynchronizedTest synchronizedTest1 = new SynchronizedTest();
            synchronizedTest1.printA();
        }).start();

        /**
         * 线程2，执行该实例的printB方法
         */
        new Thread(() -> {
            // 创建一个对象实例
            SynchronizedTest synchronizedTest2 = new SynchronizedTest();
            synchronizedTest2.printB();
        }).start();

        /**
         * 线程3，执行该实例的printC方法
         */
        new Thread(synchronizedTest::printC).start();
    }
}

class SynchronizedTest {

    /**
     * 同步方法1
     */
    public synchronized void printA() {
        System.out.println("AAAAAAAAAAAAAAAAA");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步方法2
     */
    public synchronized void printB() {
        System.out.println("BBBBBBBBBBBBBBBBB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通成员方法1
     */
    public void printC() {
        System.out.println("CCCCCCCCCCCCCCCCC");
    }
}
