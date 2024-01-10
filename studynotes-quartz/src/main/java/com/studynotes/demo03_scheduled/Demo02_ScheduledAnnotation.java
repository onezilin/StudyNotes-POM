package com.studynotes.demo03_scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description:
 */
@Slf4j
@Component
public class Demo02_ScheduledAnnotation {

    private static int count = 0;

    /**
     * Description: 和 scheduleWithFixedDelay 一样，只是使用了注解的方式
     * <p>
     * 10000 是指此次任务距上次任务执行完成的时间间隔
     */
    // @Scheduled(fixedDelay = 10000)
    void fixedDelay() {
        log.info("任务 {} 第 {} 次执行", "fixedDelay", ++count);
        try {
            if (count == 2)
                Thread.sleep(21000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行完了");
    }

    // @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    void initialDelayFixedDelay() {
        log.info("任务 {} 第 {} 次执行", "initialDelayFixedDelay", ++count);
        try {
            if (count == 2)
                Thread.sleep(21000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行完了");
    }

    /**
     * Description: 和 scheduleAtFixedRate 一样，只是使用了注解的方式
     * <p>
     * 10000 是指每次任务的两次执行的时间间隔
     */
    // @Scheduled(fixedRate = 10000)
    void fixedRate() {
        log.info("任务 {} 第 {} 次执行", "fixedRate", ++count);
        try {
            if (count == 2)
                Thread.sleep(21000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行完了");
    }

    // @Scheduled(initialDelay = 1000, fixedRate = 10000)
    void initialDelayFixedRate() {
        log.info("任务 {} 第 {} 次执行", "initialDelayFixedRate", ++count);
        try {
            if (count == 2)
                Thread.sleep(21000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行完了");
    }

    // 使用 corn 表达式配置定时任务
    // @Scheduled(cron = "5/10 * * * * ?")
    void corn() {
        log.info("任务 {} 第 {} 次执行", "corn", ++count);
        try {
            if (count == 2)
                Thread.sleep(21000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行完了");
    }
}
