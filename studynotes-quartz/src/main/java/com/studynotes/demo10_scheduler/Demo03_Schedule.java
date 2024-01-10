package com.studynotes.demo10_scheduler;

import com.studynotes.demo04_quartz.MyJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: Scheduler 关联 JobDetail 和 Trigger，有以下几种写法：
 */
@Slf4j
public class Demo03_Schedule {

    Scheduler scheduler;
    JobDetail jobDetail;
    Trigger trigger;

    @SneakyThrows
    public Demo03_Schedule() {
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "jobGroup")
                .build();
        scheduler = StdSchedulerFactory.getDefaultScheduler();
    }

    @Test
    @SneakyThrows
    public void test() {
        // 直接关联 JobDetail 和 Trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Test
    @SneakyThrows
    public void test2() {
        Set<Trigger> set = new HashSet<>();
        set.add(trigger);

        // 一个 JobDetail 关联 Trigger
        scheduler.scheduleJob(jobDetail, set, true);
    }

    @Test
    @SneakyThrows
    public void test3() {
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                // 关联指定 JobDetail
                .forJob(jobDetail)
                .build();

        // 添加 JobDetail
        scheduler.addJob(jobDetail, true);
        // 添加 Trigger
        scheduler.scheduleJob(trigger);
    }
}
