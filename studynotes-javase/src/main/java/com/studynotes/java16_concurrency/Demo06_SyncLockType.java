package com.studynotes.java16_concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Description: synchronized 类锁和对象锁之间使用的区别
 */
public class Demo06_SyncLockType {
    public static void main(String[] args) {
        Example example = new Example();
        Thread thread1 = new Thread() {
            public void run() {
                Example.exec();
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                example.exec2();// 更换成不同的方法尝试
            }
        };

        thread1.start();
        thread2.start();
    }
}

class Example {
    // 代码块锁（类锁），只有一个线程可以同时执行此代码
    public static void exec() {
        synchronized (Example.class) {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.printf("%s,Hello%d\n", Thread.currentThread()
                        .getName(), i);
            }
        }

    }

    // 代码块锁（对象锁），不同线程的不同对象可以执行
    public void exec2() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.printf("%s,Hello%d\n", Thread.currentThread()
                        .getName(), i);
            }
        }

    }

    // 方法锁（对象锁），不同线程的不同对象可以执行
    public synchronized void exec3() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.printf("%s,Hello%d\n", Thread.currentThread().getName(),
                    i);
        }

    }

    // 静态方法锁（类锁），只有一个线程可以同时执行此代码
    public synchronized static void exec4() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.printf("%s,Hello%d\n", Thread.currentThread().getName(),
                    i);
        }

    }

    // 无锁
    public void exec5() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.printf("%s,Hello%d\n", Thread.currentThread().getName(),
                    i);
        }

    }
}
