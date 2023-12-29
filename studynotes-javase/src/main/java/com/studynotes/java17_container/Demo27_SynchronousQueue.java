package com.studynotes.java17_container;

import java.util.concurrent.SynchronousQueue;

// SynchronousQueue阻塞队列测试
public class Demo27_SynchronousQueue {
    public static void main(String[] args) {

        final SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Thread putThread = new Thread(() -> {
            System.out.println("put thread start");
            try {
                queue.put(1);
            } catch (InterruptedException e) {
            }
            System.out.println("put thread end");
        });

        Thread takeThread = new Thread(() -> {
            System.out.println("take thread start");
            try {
                System.out.println("take from putThread: " + queue.take());
            } catch (InterruptedException e) {
            }
            System.out.println("take thread end");
        });

        putThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takeThread.start();
    }
}
