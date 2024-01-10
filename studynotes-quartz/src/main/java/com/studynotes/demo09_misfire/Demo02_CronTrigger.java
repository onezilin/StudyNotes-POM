package com.studynotes.demo09_misfire;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

/**
 * Description: Job 到达触发时间时还没有被执行，并且延迟时间超过了 misfireThreshold 阈值，Quartz 提供了 Misfire 机制来处理这种情况
 * <p>
 * 测试 CronTrigger 的 Misfire 机制
 */
public class Demo02_CronTrigger {

    // 只会执行第一次错过的任务
    @Test
    public void test1() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("0 0 9-18 ? * 2 *") // 每周一 9:00-18:00 每个整点执行一次
                .withMisfireHandlingInstructionFireAndProceed(); // 默认值
        misfire(scheduleBuilder);
    }

    // 会并发执行所有错过的任务
    @Test
    public void test2() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("0 0 9-18 ? * 2 *")
                .withMisfireHandlingInstructionIgnoreMisfires();
        misfire(scheduleBuilder);
    }

    // 不做任何处理
    @Test
    public void test3() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule("0 0 9-18 ? * 2 *")
                .withMisfireHandlingInstructionDoNothing();
        misfire(scheduleBuilder);
    }

    @SneakyThrows
    private void misfire(ScheduleBuilder<? extends Trigger> scheduleBuilder) {
        JobDetail jobDetail = JobBuilder.newJob(Demo01_MisfireJob.class)
                .withIdentity("myJob", "jobGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                // 10:00:00 开始执行，当前时间 14:30:00，执行 misfire 策略
                .startAt(DateBuilder.dateOf(10, 0, 0))
                .withIdentity("myTrigger", "triggerGroup")
                .withSchedule(scheduleBuilder)
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }
}
