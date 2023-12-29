package com.studynotes.java16_concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// CompletableFuture中线程池创建的线程都是守护线程，当所有非守护线程关闭时，JVM也会退出，导致守护线程中断
@Slf4j
public class Demo41_NoDaemon {
    public static void main(String[] args) {
        //        defaultThreadPool();

        //        defaultThreadPool1();

        //        defaultThreadPool2();

        customizedThreadPool();
    }

    public static void defaultThreadPool() {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            log.info("future开始了");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行完了");
        });
        log.info("主线程执行完毕，并且future没有调用阻塞方法");
    }

    public static void defaultThreadPool1() {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            log.info("future开始了");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行完了");
        });
        log.info("主线程执行完毕，future调用阻塞方法");
        future.join();
    }

    public static void defaultThreadPool2() {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            log.info("future开始了");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行完了");
        });
        log.info("主线程执行完毕，并且future没有调用阻塞方法，主线程睡了10秒");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 传入自定义线程池
    public static void customizedThreadPool() {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
                    log.info("future开始了");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("我执行完了");
                }, new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)))
                .thenAcceptAsync((i) -> {
                    log.info("thenAcceptAsync");
                });
        log.info("主线程执行完毕，并且future没有调用阻塞方法，但是传入自定义线程池");
    }
}
