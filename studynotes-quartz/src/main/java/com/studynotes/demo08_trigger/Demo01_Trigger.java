package com.studynotes.demo08_trigger;

import org.quartz.*;

import java.util.Date;

/**
 * Description: 通过不同的 ScheduleBuilder，创建不同的Trigger
 */
public class Demo01_Trigger {

    public static Trigger getSimpleTrigger() {
        // 创建一个 SimpleScheduleBuilder，执行间隔为 1s
        ScheduleBuilder<SimpleTrigger> scheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever();
        return getTrigger(scheduleBuilder);
    }

    public static Trigger getCronTrigger() {
        // 每年每月周一到周五15:00执行定时任务
        ScheduleBuilder<CronTrigger> scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 15 ? * 2-6 *");
        return getTrigger(scheduleBuilder);
    }

    private static Trigger getTrigger(ScheduleBuilder<? extends Trigger> scheduleBuilder) {
        // 5s 后开始，10s 后结束
        Date now = new Date();
        Date startTime = new Date(now.getTime() + 5000);
        Date endTime = new Date(now.getTime() + 10000);

        return TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .startAt(startTime)
                .endAt(endTime)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
