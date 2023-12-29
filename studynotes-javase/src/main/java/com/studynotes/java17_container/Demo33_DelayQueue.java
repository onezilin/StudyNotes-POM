package com.studynotes.java17_container;

import lombok.Data;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Description: debug DelayQueue
 */
public class Demo33_DelayQueue {

    private static final DelayQueue<ItemDelayed<String>> DELAY_QUEUE = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {
        ItemDelayed<String> itemDelayed = new ItemDelayed<>(5, "hello");
        DELAY_QUEUE.put(itemDelayed);

        while (true) {
            ItemDelayed<String> item = DELAY_QUEUE.take();
            System.out.println(new Date() + " " + item.getData());
        }
    }

    @Data
    static class ItemDelayed<T> implements Delayed {
        private final long expire;

        private final T data;

        private String itemId;

        private long startTime;

        public ItemDelayed(long secondsDelay, T data) {
            this.startTime = System.currentTimeMillis();
            this.expire = this.startTime + secondsDelay * 1000;
            this.data = data;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), unit);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
