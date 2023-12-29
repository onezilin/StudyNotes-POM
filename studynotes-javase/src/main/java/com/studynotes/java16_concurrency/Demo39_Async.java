package com.studynotes.java16_concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

// 测试CompletableFuture中调用complete是有无async结尾方法的执行区别
@Slf4j
public class Demo39_Async {

    public static void main(String[] args) throws Exception {

        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("执行我咯");
            return 10;
        });

        new Thread(() -> {
            // 子线程A启动
            log.info("子线程A启动");
            try {
                log.info("子线程A沉睡5s");
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 当子线程A执行到 f.complete 的时候，会去看是否有注册好的f的then或者when（非async的），如果有的话，会顺次去执行。
            log.info("子线程 A 令future完成，返回值：{}，子线程A结束。", f.complete(100));
        }).start();

        // 查看这两个输出的区别
        // noAsync(f);
        noAsync2(f);

        System.in.read();
    }

    public static void noAsync(CompletableFuture f) {
        // 当前线程（主线程）执行到这里的时候，如果子线程还没有执行到f.complete(100)，
        // 那么当前线程会把whenComplete事件注册起来，并且说好哪个线程执行了f.complete(100)，
        // 哪个线程就负责执行whenComplete的内容。
        when(f);
    }

    public static void noAsync2(CompletableFuture f) {
        // 如果当前线程（主线程）执行到这里的时候，f.complete(100)已经被其他线程执行完毕了。
        // 那么只有当前线程自己来执行whenComplete里面的内容了。
        try {
            log.info("主线程沉睡10s");
            Thread.sleep(10000);
            log.info("主线程醒过来了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        when(f);
    }

    public static void when(CompletableFuture f) {
        f.thenAccept((i) -> {
            log.info("呵呵哈哈哈" + i);
        }).thenAcceptAsync(i -> {
            log.info("呵呵呵呵呵" + i);
        }).whenComplete((i, ex) -> {
            // 这个场景下，whenComplete的回调的执行线程会是主线程
            log.info("do something after complete begin");
            System.out.println("异步值：" + i);
            System.out.println("异常值：" + ex);
            try {
                log.info("沉睡10s");
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i = 10000;
            System.out.println("异步值：" + i);
            System.out.println("异常值：" + ex);
            log.info("do something after complete end");
        });
    }
}
