package com.studynotes.java16_concurrency;

/**
 * Description: 买票场景模拟多线程下的，加锁和不加锁，对于共享资源的超卖现象
 */
public class Demo12_LockAndUnlockDiff {
    public static void main(String[] args) {

    }
}

class Ticket implements Runnable {
    int ticket = 100;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            if (this.ticket > 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在卖票:" + ticket--);
            }
        }
    }

    public static void main(String[] args) {
        //		创建票对象
        Ticket ticket = new Ticket();

        //      创建3个窗口
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Ticket2 implements Runnable {
    int ticket = 100;

    Object object = new Object();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            synchronized (object) {
                if (this.ticket > 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在卖票:" + ticket--);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 加了互斥锁
        Ticket2 ticket = new Ticket2();

        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
