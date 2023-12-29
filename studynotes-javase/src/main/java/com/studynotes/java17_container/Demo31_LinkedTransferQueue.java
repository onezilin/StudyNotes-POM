package com.studynotes.java17_container;

import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Description: debug LinkedTransferQueue
 */
public class Demo31_LinkedTransferQueue {

    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

        // 测试put方法，如果有消费者在等待，则直接传递给消费者，否则直接放入队列
        new Thread(() -> {
            IntStream.range(0, 1).forEach(i -> linkedTransferQueue.put("helloPut" + i));
            System.out.println("put hello");
        }).start();

        Thread.sleep(1000);

        // 测试transfer方法，如果有消费者在等待，则直接传递给消费者，否则阻塞当前线程直到被消费
        new Thread(() -> {
            try {
                linkedTransferQueue.transfer("helloTransfer");
                System.out.println("transfer hello");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(1000);

        // 测试tryTransfer方法，如果有消费者在等待，则直接传递给消费者；否则放入队列，直到过期时从队列中移除
        new Thread(() -> {
            try {
                linkedTransferQueue.tryTransfer("helloTryTransfer", 5, TimeUnit.SECONDS);
                System.out.println("tryTransfer hello");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(1000);

        System.out.println(linkedTransferQueue.size());

        Thread.sleep(3000);

        new Thread(() -> {
            IntStream.range(0, 3).forEach(i -> {
                try {
                    System.out.println(linkedTransferQueue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("take hello");
        }).start();

        System.in.read();
    }
}
