package com.studynotes.java16_concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// FutureTask测试
@Slf4j
@ConditionalOnMissingBean
public class Demo37_FutureTask {
    public static void main(String[] args) {
        Instant start = Instant.now();
        FutureTask<Integer> futureTask1 = new FutureTask<>(() -> {
            Thread.sleep(3000);
            return 5;
        });

        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            Thread.sleep(2000);
            return 3;
        });

        // 线程开启
        new Thread(futureTask1).start();
        new Thread(futureTask2).start();
        try {
            // 获取futureTask返回值，这里会阻塞main线程，会等待任务执行完
            int futureResult1 = futureTask1.get();
            int futureResult2 = futureTask2.get();

            log.info("结果：{}，用时：{}s", futureResult1 - futureResult2,
                    Duration.between(start, Instant.now()).get(ChronoUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
