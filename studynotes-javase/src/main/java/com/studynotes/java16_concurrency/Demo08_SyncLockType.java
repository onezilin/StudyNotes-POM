package com.studynotes.java16_concurrency;

/**
 * Description: synchronized 方法锁和代码块锁，以及对象锁和类锁的关系
 */
public class Demo08_SyncLockType {
    public static void main(String[] args) {

        // 锁对象,对于同步代码块和同步方法一样的原理,只是同步代码块和同步方法的作用域不同

        SynchronizedObjTest synchronizedObjTest1 = new SynchronizedObjTest();
        /**
         * 线程1，执行该实例的printA方法
         */
        new Thread(synchronizedObjTest1::printA).start();
        /**
         * 线程2，执行该实例的printA方法
         */
        new Thread(synchronizedObjTest1::printA).start();

        /**
         * 线程3，执行该实例的printB方法
         */
        new Thread(() -> {
            SynchronizedObjTest synchronizedObjTest = new SynchronizedObjTest();
            synchronizedObjTest.printB();

        }).start();

        /**
         * 线程4，执行该实例的printC方法
         */
        new Thread(() -> {
            SynchronizedObjTest synchronizedObjTest = new SynchronizedObjTest();
            synchronizedObjTest.printC();
        }).start();

        /**
         * 线程5，执行该实例的printD方法
         */
        new Thread(SynchronizedObjTest::printD).start();

        /**
         * 线程6，执行该实例的printD方法
         */
        new Thread(SynchronizedObjTest::printD).start();
    }
}

class SynchronizedObjTest {
    /**
     * 同步方法1
     */
    public void printA() {
        System.out.println("printA");
        synchronized (this) {
            System.out.println("AAAAAAAAAAAAAAAAA");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步方法2
     */
    public void printB() {
        System.out.println("printB");
        synchronized (this) {
            System.out.println("BBBBBBBBBBBBBBBBB");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步方法3
     */
    public synchronized void printC() {
        System.out.println("printC");
        System.out.println("CCCCCCCCCCCCCCCCC");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步方法4
     */
    public static synchronized void printD() {
        System.out.println("printD");
        System.out.println("DDDDDDDDDDDDDDDDDD");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
