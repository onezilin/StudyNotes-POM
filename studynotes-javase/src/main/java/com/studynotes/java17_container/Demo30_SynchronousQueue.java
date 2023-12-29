package com.studynotes.java17_container;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

/**
 * Description: debug SynchronousQueue
 */
public class Demo30_SynchronousQueue {

    private static final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>(true);


    public static void main(String[] args) throws IOException, InterruptedException {


        new Thread(() -> {
            try {
                synchronousQueue.put("hello");
                System.out.println("put hello");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(5000);

        new Thread(() -> {
            try {
                System.out.println(synchronousQueue.take());
                System.out.println("take hello");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        System.in.read();
    }
}
