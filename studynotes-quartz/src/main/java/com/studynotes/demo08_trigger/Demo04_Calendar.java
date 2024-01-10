package com.studynotes.demo08_trigger;

import com.studynotes.demo04_quartz.MyJob;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Description: Quartz 提供 Calendar，用于指定排除的日期，让 Trigger 不会在这些日期触发
 */
public class Demo04_Calendar {

    @Test
    @SneakyThrows
    public void test() {
        // 创建一个 Quartz 的 Calendar 实例，指定排除的日期
        AnnualCalendar holidays = new AnnualCalendar();
        ArrayList<Calendar> daysExcluded = new ArrayList<>();

        Calendar newTearsDay = new GregorianCalendar(2024, Calendar.JANUARY, 1);
        daysExcluded.add(newTearsDay);
        Calendar labourDay = new GregorianCalendar(2024, Calendar.MAY, 1);
        daysExcluded.add(labourDay);
        Calendar nationalDay = new GregorianCalendar(2024, Calendar.OCTOBER, 1);
        daysExcluded.add(nationalDay);
        /**
         * Description:
         * 测试步骤：
         * 1、将下面这行代码注释掉，运行测试方法，可以看到每秒都会触发一次 MyJob
         * 2、取消注释，运行测试方法，可以看到 2024-01-10 这一天不会触发 MyJob
         */
        // Calendar today = new GregorianCalendar(2024, Calendar.JANUARY, 10);
        // daysExcluded.add(today);
        holidays.setDaysExcluded(daysExcluded);

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 添加到 Scheduler 中
        scheduler.addCalendar("holidays", holidays, true, true);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                // Trigger 使用指定的 Calendar
                .modifiedByCalendar("holidays")
                .build();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "jobGroup")
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }
}
