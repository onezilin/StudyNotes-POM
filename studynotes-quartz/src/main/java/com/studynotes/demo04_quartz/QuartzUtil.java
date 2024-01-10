package com.studynotes.demo04_quartz;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description: Quartz 使用
 */
@Slf4j
public class QuartzUtil {

    public static void main(String[] args) {
        quartzSchedule(MyJob.class);
    }

    @SneakyThrows
    public static void quartzSchedule(Class<? extends Job> jobClass) {
        quartzSchedule(jobClass, new JobDataMap(), new JobDataMap());
    }

    @SneakyThrows
    public static void quartzSchedule(Class<? extends Job> jobClass, JobDataMap detailDataMap,
                                      JobDataMap triggerDataMap) {
        // 1、创建 JobDetail，将 Job 封装成 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                // name + group 必须唯一
                .withIdentity("myJob", "jobGroup")
                // 添加 JobDataMap
                .usingJobData(detailDataMap)
                .build();

        // 2、创建 Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                // 添加 JobDataMap
                .usingJobData(triggerDataMap)
                // 立即启动
                .startNow()
                // 10s 后触发器停止
                .endAt(new Date(new Date().getTime() + 10_000))
                // 构建触发器执行策略，每隔 1s 执行一次
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

        // 3、创建 Scheduler，将 JobDetail 和 Trigger 关联起来，并执行
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);

        log.info("---------schduler start!------------");
        scheduler.start();
        TimeUnit.MINUTES.sleep(10);
        log.info("---------scheduler shutdown!---------------------------");
        scheduler.shutdown();
    }
}
