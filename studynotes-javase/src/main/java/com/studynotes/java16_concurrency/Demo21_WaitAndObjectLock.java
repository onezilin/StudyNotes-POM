package com.studynotes.java16_concurrency;

// wait和调用wait的锁对象关系
public class Demo21_WaitAndObjectLock {
    public static void main(String[] args) {
        //        错误的写法, 这里myThread1和myThread2操作的是两个不同的对象.
        //        Thread myThread1 = new Thread(new MyThread1(new String("爱吃土豆")));
        //        Thread myThread2 = new Thread(new MyThread2(new String("爱吃土豆")));

        //        正确的写法, 这里myThread1和myThread2操作的是同一个对象.
        String str = "爱吃土豆";
        Thread myThread1 = new Thread(new MyThread1(str));
        Thread myThread2 = new Thread(new MyThread2(str));
        myThread1.start();
        myThread2.start();
    }
}

class MyThread1 implements Runnable {
    private Object obj;

    public MyThread1(Object o) {
        obj = o;
    }

    public void run() {
        synchronized (obj) { // 这里是给obj对象(也就是str="爱吃土豆")加锁, 如写成synchronized (this), 则表示是给myThread1加锁。
            try {
                System.out.println("MyThread1进入wait状态");
                obj.wait();
                System.out.println("MyThread1被notify");
            } catch (InterruptedException e) {
                System.err.println("谁把我吵醒了.....");
            }
        }
    }
}

class MyThread2 implements Runnable {
    private Object obj;

    public MyThread2(Object o) {
        obj = o;
    }

    public void run() {
        synchronized (obj) {  // 这里是给obj对象(也就是str="爱吃土豆")加锁, 如写成synchronized (this), 则表示是给myThread2加锁。
            System.out.println("MyThread2调用notify()方法");
            obj.notify();
        }
    }
}
