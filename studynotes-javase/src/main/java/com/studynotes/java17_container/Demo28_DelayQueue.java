package com.studynotes.java17_container;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Description: DelayQueue实现BlockingQueue
 */
@Slf4j
public class Demo28_DelayQueue {

    /**
     * Description: 添加到DelayQueue中的元素需要实现Delayed接口
     *
     * @author Wanzilin
     * @since 2021/9/27 16:59
     */
    @Data
    class MyDelayTask implements Delayed {

        private long timeout;

        private String name;

        public MyDelayTask(long timeout, String name, TimeUnit unit) {
            this.timeout = System.currentTimeMillis() + (timeout > 0 ? unit.toMillis(timeout) : 0);
            this.name = name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            MyDelayTask myDelayTask = (MyDelayTask) o;
            long diff = this.timeout - myDelayTask.getTimeout();
            return diff <= 0 ? -1 : 1;
        }
    }

    @Test
    public void delayQueueTest() {
        DelayQueue<MyDelayTask> delayQueue = new DelayQueue<>();
        MyDelayTask task1 = new MyDelayTask(10, "task1", TimeUnit.SECONDS);
        MyDelayTask task2 = new MyDelayTask(5, "task2", TimeUnit.SECONDS);
        MyDelayTask task3 = new MyDelayTask(15, "task3", TimeUnit.SECONDS);
        Collections.addAll(delayQueue, task1, task2, task3);


        IntStream.range(0, 4).forEach(i -> {
            Thread thread = new Thread(() -> {
                try {
                    MyDelayTask task = delayQueue.take();
                    log.info("task: {}", task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
