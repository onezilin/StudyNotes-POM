package com.studynotes.java16_concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: 并发和同步时间比较
 */
public class Demo11_SyncAndAsyncDiff {
    public static void main(String[] args) {
        ContentSwitchTest.concurrency();
        ContentSwitchTest.serial();
    }
}

class ContentSwitchTest {
    private static final long COUNT = 10000000000L;

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void concurrency() {
        long start = System.currentTimeMillis();
        threadPoolExecutor.execute(() -> {
            int a = 0;
            for (long i = 0; i < COUNT; i++) {
                a += 5;
            }
            System.out.println(a);
        });
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }
        threadPoolExecutor.shutdown();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);

    }

    public static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < COUNT; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }
}
