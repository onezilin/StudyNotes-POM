package com.studynotes.demo02_scheduleThreadPoolExecutor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Description: 使用 ScheduledThreadPoolExecutor 定时任务线程池执行任务
 */
public class Demo03_ScheduledThreadPoolExecutor {

    // 试一试核心线程数大于 1 的情况
    private ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
    private int count = 0;

    @Test
    void test1() {
        for (int i = 0; i < 1; i++) {
            Runnable task = new Demo01_Runnable(String.valueOf(i), count);
            // 10s 后执行一次
            schedule.schedule(task, 10, TimeUnit.SECONDS);
        }
    }

    @Test
    void test2() {
        for (int i = 0; i < 10; i++) {
            Runnable task = new Demo01_Runnable(String.valueOf(i), count);
            // 0s 后开始执行，每过 10s 执行一次
            schedule.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
        }
    }

    @Test
    void test3() {
        for (int i = 0; i < 1; i++) {
            Runnable task = new Demo01_Runnable(String.valueOf(i), count);
            // 0s 后开始执行，每过（上次定时任务执行完后 + 10s）执行一次
            schedule.scheduleWithFixedDelay(task, 0, 10, TimeUnit.SECONDS);
        }
    }

    @Test
    void test4() {
        for (int i = 0; i < 1; i++) {
            // 传入Callable，通过Future获取返回值
            Callable<String> task = new Demo02_Callable(String.valueOf(i), count);
            // 两秒后执行一次
            Future<String> future = schedule.schedule(task, 10, TimeUnit.SECONDS);
            try {
                System.out.println(future.get(10, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }
}
