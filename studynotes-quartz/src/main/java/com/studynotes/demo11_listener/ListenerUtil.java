package com.studynotes.demo11_listener;

import com.studynotes.demo04_quartz.MyJob;
import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.function.Consumer;

/**
 * Description: 监听器工具类，用于简化监听器的添加
 */
public class ListenerUtil {

    /**
     * Description:
     *
     * @param consumer 用于提供 ListenerManager 让外部添加监听器
     */
    @SneakyThrows
    public static void schedule(Consumer<ListenerManager> consumer) {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        ListenerManager listenerManager = scheduler.getListenerManager();
        consumer.accept(listenerManager);

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "jobGroup")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

        Thread.sleep(10000);
        scheduler.clear();
        scheduler.shutdown();
    }
}
