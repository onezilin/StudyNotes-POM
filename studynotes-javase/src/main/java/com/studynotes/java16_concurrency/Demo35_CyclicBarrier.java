package com.studynotes.java16_concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// CyclicBarrier 测试
// 和CountDownLatch区别是：
// 1、CountDownLatch的number变量为0后，不可以重来，而Cyclibarrier的number为0后，又变成初始值
// 2、Cyclibarrier是阻塞自己，CountDownLatch是阻塞其他线程
//
public class Demo35_CyclicBarrier {
    private static final int COUNT = 5;
    private static final CyclicBarrier BARRIER = new CyclicBarrier(COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                System.out.println("线程启动");
                try {
                    Thread.sleep(3000);
                    System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                    BARRIER.await(1, TimeUnit.SECONDS);
                    System.out.println("所有线程写入完毕，继续处理其他任务...");
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // 重置屏障
        BARRIER.reset();

        for (int i = 0; i < COUNT; i++) {
            new Thread(() -> {
                System.out.println("线程启动");
                try {
                    Thread.sleep(3000);
                    System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                    BARRIER.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
                System.out.println("所有线程写入完毕，继续处理其他任务...");
            }).start();
        }
    }
}
