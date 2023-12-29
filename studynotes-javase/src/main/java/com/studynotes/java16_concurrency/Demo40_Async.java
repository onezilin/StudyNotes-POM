package com.studynotes.java16_concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

// 测试正常的使用中有无async结尾的方法执行区别
@Slf4j
public class Demo40_Async {

    @Test
    public void noAsync() throws InterruptedException {
        log.info("执行主线程");
        List<Integer> list = new ArrayList<>();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            log.info("开始查询");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("结束查询");
            return 10;
        });
        // future、future1、future2使用的是同一个线程池中的线程
        CompletableFuture future1 = future.thenAccept(i -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(i);
            log.info("thenAccept1");
        });
        CompletableFuture future2 = future.thenAccept(i -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("thenAccept2");
        });

        CompletableFuture.allOf(future, future1, future2).join();
        log.info("执行完毕！");

    }

    @Test
    public void async() throws InterruptedException {
        log.info("执行主线程");
        List<Integer> list = new ArrayList<>();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            log.info("开始查询");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("结束查询");
            return 10;
        });
        // future、future1、future2使用的是线程池中不同的线程（当然也有可能使用的是同一个线程）
        CompletableFuture future1 = future.thenAcceptAsync(i -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(i);
            log.info("thenAccept1");
        });
        CompletableFuture future2 = future.thenAcceptAsync(i -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("thenAccept2");
        });

        CompletableFuture.allOf(future, future1, future2).join();
        log.info("执行完毕！");

    }
}
