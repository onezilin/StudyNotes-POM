package com.studynotes.demo08_trigger;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;

/**
 * Description: 相同时间多个 Trigger 同时触发且工作线程数小于 Trigger 数量时，优先级高（数字大）的 Trigger 先执行
 */
@Slf4j
public class Demo03_Priority {

    /**
     * Description:
     * 预期结果：
     * 1、优先级高的 Trigger 先执行，因此执行顺序为 trigger10、trigger5、trigger1
     */
    @Test
    @SneakyThrows
    public void test() {
        JobDetail jobDetail = JobBuilder.newJob(Demo02_PriorityJob.class)
                .withIdentity("myJob", "jobGroup")
                .build();

        Date now = new Date();
        Trigger trigger5 = getTrigger(now, 5, "trigger5", jobDetail);
        Trigger trigger1 = getTrigger(now, 1, "trigger1", jobDetail);
        Trigger trigger10 = getTrigger(now, 10, "trigger10", jobDetail);

        Properties properties = new Properties();
        // 工作线程为 1，优先级高的 Trigger 先执行
        properties.setProperty("org.quartz.threadPool.threadCount", "1");
        Scheduler scheduler = new StdSchedulerFactory(properties).getScheduler();

        HashSet<Trigger> triggers = new HashSet<>();
        triggers.add(trigger1);
        triggers.add(trigger5);
        triggers.add(trigger10);
        scheduler.scheduleJob(jobDetail, triggers, true);
        scheduler.start();
    }

    private Trigger getTrigger(Date startTime, int priority, String name, JobDetail jobDetail) {
        TriggerBuilder<SimpleTrigger> builder = TriggerBuilder.newTrigger()
                .withIdentity(name, "triggerGroup")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .withPriority(priority)
                .forJob(jobDetail);
        if (jobDetail != null) {
            builder.forJob(jobDetail);
        }
        return builder.build();
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }
}


