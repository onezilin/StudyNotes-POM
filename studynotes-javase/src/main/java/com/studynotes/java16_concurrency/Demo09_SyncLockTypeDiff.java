package com.studynotes.java16_concurrency;

//

//
// 首先synchronized 方法和synchronized 代码块只是作用的范围不同，所以统一描述为synchronized
//
//

/**
 * Description: 类锁和同步代码块
 * <p>
 * 关于类锁、对象锁、synchronized 方法、synchronized 代码块、static synchronized：
 * 首先synchronized 方法和synchronized 代码块只是作用的范围不同，所以统一描述为synchronized
 * * 对象锁: 是获取当前调用的对象作为锁,一个线程获取该对象锁后,其他线程不可以使用同一个对象锁,但是可以使用同一个类的不同对象锁
 * * 类锁: 获取当前类的class对象作为锁,一个线程获取类锁后,其他线程不可以使用同一个类锁,因为已给类只用一个class对象
 * <p>
 * synchronized: 可以用类修饰方法和代码块,对于对象锁,在同一个对象中,多线程中只能一个拥有对象锁的线程能访问被synchronized(对象锁)修饰的区域,其他线程都不可以,
 * 不同的对象则没有影响;对于类锁,这个类创建的所有对象中,多线程中只能一个拥有类锁,访问被synchronized(类锁)修饰的区域;对于两个个线程同时访问
 * synchronized(对象锁) synchronized(类锁),因为锁的机制不同,所有不会产生冲突的情况
 * <p>
 * static synchronized: 修饰(静态)方法,相当于有一把类锁,一个线程获取锁后，其他线程不允许再访问。static synchronized 和 synchronized
 * 修饰的不同方法可以被两个线程同时访问,原理和上面类似.
 */
public class Demo09_SyncLockTypeDiff {
    public static void main(String[] args) {

        /**
         * 线程1，执行该实例的printA方法
         */
        Thread thread1 = new Thread(() -> {
            SynchronizedClassTest synchronizedClassTest1 = new SynchronizedClassTest();
            synchronizedClassTest1.printA();
        });

        thread1.start();
        // 放在这里会打断线程
        thread1.interrupt();

        /**
         * 线程2，执行该实例的printB方法
         */
        new Thread(() -> {
            SynchronizedClassTest synchronizedClassTest1 = new SynchronizedClassTest();
            synchronizedClassTest1.printC();
        }).start();
    }
}

class SynchronizedClassTest {
    /**
     * 同步方法1
     */
    public void printA() {
        // 类锁,获取类的class对象,对该类的所有对象起作用,当一个线程获取类锁后,可以防止其他多个线程访问这个类创建的对象,
        synchronized (SynchronizedClassTest.class) {
            System.out.println("AAAAAAAAAAAAAAAAA");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步方法2
     */
    public void printB() {
        synchronized (this) {
            System.out.println("BBBBBBBBBBBBBBBBB");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步方法3
     */
    public static synchronized void printC() {
        System.out.println("CCCCCCCCCCCCCCCCCCC");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步方法4
     */
    public static synchronized void printD() {
        System.out.println("DDDDDDDDDDDDDDDDDDDDDD");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

