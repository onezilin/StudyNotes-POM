package com.studynotes.java16_concurrency;

/**
 * Description: Sleep 方法
 */
public class Demo16_Sleep {
    public static void main(String[] args) {
        ThreadSleep threadSleep = new ThreadSleep();
        Thread[] threads = new Thread[5];
        System.out.println(Thread.currentThread().getName() + "线程的状态为：" + Thread.currentThread().getState());
        for (Thread thread : threads) {
            thread = new Thread(threadSleep);
            thread.start();
            if ("Thread-1".equals(thread.getName()) || "Thread-3".equals(thread.getName()))
                thread.interrupt();
        }
        System.out.println("111111111111");
    }
}

// sleep会让使用sleep方法的线程阻塞,并且不会释放同步锁,而且这期间不会阻碍其他线程继续运行
class ThreadSleep implements Runnable {

    public void run() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + ", sleep()前");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + ", sleep()后");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ", 谁把我吵醒了.....");
            }
            System.out.println(Thread.currentThread().getName() + ", run()结束..进入TERMINATED状态");
        }
    }
}
