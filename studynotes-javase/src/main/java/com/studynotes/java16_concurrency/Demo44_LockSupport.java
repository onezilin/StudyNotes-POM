package com.studynotes.java16_concurrency;

import java.util.List;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 */
public class Demo44_LockSupport {
    public static void main(String[] args) {
        System.out.println("before park");
        // 测试多线程在 LockSipport 中存储的数据
        Thread t2 = new Thread(() -> {
            // 测试中断对 park 的影响
            System.out.println("启动线程2, " + Thread.currentThread().isInterrupted());
            LockSupport.park("线程2 也阻塞了, " + Thread.currentThread().isInterrupted());
            System.out.println("唤醒线程2, " + Thread.currentThread().isInterrupted());
        });
        t2.start();
        MyThread myThread = new MyThread(Thread.currentThread(), t2);
        myThread.start();
        LockSupport.park("ParkAndUnparkDemo");
        System.out.println("after park");
    }

    static class MyThread extends Thread {
        private final List<Object> object;

        public MyThread(Object... object) {
            this.object = Stream.of(object).collect(Collectors.toList());
        }

        public void run() {
            System.out.println("before unpark");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object.get(0)));
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object.get(1)));
            // 释放许可
            LockSupport.unpark((Thread) object.get(0));
            // 休眠500ms，保证先执行park中的setBlocker(t, null);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 再次获取blocker
            System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object.get(1)));

            ((Thread) object.get(1)).interrupt();

            System.out.println("after unpark");
        }
    }
}
