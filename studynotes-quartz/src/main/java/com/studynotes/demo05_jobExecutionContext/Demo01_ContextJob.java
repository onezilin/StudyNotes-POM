package com.studynotes.demo05_jobExecutionContext;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Description: JobExecutionContext 是调度上下文，内部包含 Job 实例、JobDetail、Trigger、Scheduler 等信息
 */
@Slf4j
public class Demo01_ContextJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Job 实例：{}", context.getJobInstance());
        log.info("this == context.getJobInstance()？{}", this == context.getJobInstance()); // true

        log.info("JobDetail：{}", context.getJobDetail());

        log.info("Trigger：{}", context.getTrigger());

        log.info("Scheduler：{}", context.getScheduler());

        log.info("上次 Job 任务执行时间：{}", context.getPreviousFireTime());
        log.info("此次 Job 任务执行时间：{}", context.getFireTime());
        log.info("下次 Job 任务执行时间：{}", context.getNextFireTime());
    }
}
