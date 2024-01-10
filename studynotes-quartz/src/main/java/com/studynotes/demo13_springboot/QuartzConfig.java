package com.studynotes.demo13_springboot;

import com.studynotes.demo12_spring.MyJobBean;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: Quartz 配置类
 */
@Configuration
public class QuartzConfig {

    /**
     * Description: 启动 Quartz 集群，查看不支持分布式和支持故障转移的含义
     * <p>
     * 测试步骤：
     * 1. JobStore 必须是 JDBCJobStore（需要持久化到数据库）
     * 2. 启动 QuartzMain、QuartzMain_Copy，查看 jon1 和 job2 的执行情况
     * 3、关闭 QuartzMain_Copy，查看 jon1 和 job2 的执行情况
     * <p>
     * 预期结果：
     * 1、同一时刻一个任务只会在一个 Quartz 服务上执行，例如 job1 在 QuartzMain 或 QuartzMain_Copy 上执行，不会出现 job1 同一时刻在
     * QuartzMain 和 QuartzMain_Copy 上执行
     * 2、当 QuartzMain_Copy 关闭后，job2 会转移到 QuartzMain 上执行
     * <p>
     * 结论：
     * 1、Quartz 不会把一个任务分成很多份，放在不同机器上执行，一个任务只会被一个具体的服务执行
     * 2、当一个服务关闭后，Quartz 会把该服务上的任务转移到其他服务上执行
     */

    @Bean
    public JobDetail myJobDetail1() {
        return JobBuilder.newJob(MyJobBean.class)
                .withIdentity("myJob1", "jobGroup")
                .usingJobData("count", 1)
                // 没有 Trigger 关联的话是否应该存储
                .storeDurably(true)
                .build();
    }

    @Bean
    public JobDetail myJobDetail2() {
        return JobBuilder.newJob(MyJobBean.class)
                .withIdentity("myJob2", "jobGroup")
                .usingJobData("count", 1)
                // 没有 Trigger 关联的话是否应该存储
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger myTrigger1() {
        return TriggerBuilder.newTrigger()
                .withIdentity("myTrigger1", "triggerGroup")
                // 关联指定 JobDetail
                .forJob(myJobDetail1())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
    }

    @Bean
    public Trigger myTrigger2() {
        return TriggerBuilder.newTrigger()
                .withIdentity("myTrigger2", "triggerGroup")
                // 关联指定 JobDetail
                .forJob(myJobDetail2())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
    }
}
