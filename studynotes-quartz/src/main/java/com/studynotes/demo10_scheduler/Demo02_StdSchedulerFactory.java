package com.studynotes.demo10_scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Description: 通过 StdSchedulerFactory 创建 Scheduler
 */
@Slf4j
public class Demo02_StdSchedulerFactory {

    @Test
    @SneakyThrows
    public void test() {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        log.info("---------schduler start!------------");
        scheduler.start();
        TimeUnit.MINUTES.sleep(10);
        log.info("---------scheduler shutdown!---------------------------");
        scheduler.shutdown();
    }
}
