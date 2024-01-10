package com.studynotes.demo10_scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.simpl.RAMJobStore;
import org.quartz.simpl.SimpleThreadPool;

import java.util.concurrent.TimeUnit;

/**
 * Description: 通过 DirectSchedulerFactory 创建 Scheduler
 */
@Slf4j
public class Demo01_DirectSchedulerFactory {

    @Test
    @SneakyThrows
    public void test() {
        DirectSchedulerFactory schedulerFactory = DirectSchedulerFactory.getInstance();
        // 需要自己创建指定的线程池和存储方式
        schedulerFactory.createScheduler(new SimpleThreadPool(10, Thread.NORM_PRIORITY), new RAMJobStore());
        Scheduler scheduler = schedulerFactory.getScheduler();

        log.info("---------schduler start!------------");
        scheduler.start();
        TimeUnit.MINUTES.sleep(10);
        log.info("---------scheduler shutdown!---------------------------");
        scheduler.shutdown();
    }
}
