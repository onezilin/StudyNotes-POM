package com.studynotes.demo07_prototype;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * Description: Job 是原型模式，每次任务执行都会创建一个新的 Job 实例
 * <p>
 * Job 实例、JobDetail（及 JobDataMap）、Trigger（及 JobDataMap）都是多例的
 * <p>
 * Scheduler 是单例的
 */
@Slf4j
public class Demo01_PrototypeJob implements Job {

    private static Job prevJob;

    private static JobDetail prevJobDetail;

    private static JobDataMap prevDetailDataMap;

    private static Trigger prevTrigger;

    private static JobDataMap prevTriggerDataMap;

    private static Scheduler prevScheduler;

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("prevJob == context.getJobInstance()？" + (prevJob == context.getJobInstance())); // false
        log.info("Job 实例：{}", context.getJobInstance());
        prevJob = context.getJobInstance();

        System.out.println("prevJobDetail == context.getJobDetail()？" + (prevJobDetail == context.getJobDetail())); // false
        log.info("JobDetail：{}；hashcode：{}", context.getJobDetail(), System.identityHashCode(context.getJobDetail()));
        prevJobDetail = context.getJobDetail();

        System.out.println("prevDetailDataMap == context.getJobDetail().getJobDataMap()？" + (prevDetailDataMap == context.getJobDetail().getJobDataMap())); // false
        log.info("detailDataMap：{}", System.identityHashCode(context.getJobDetail().getJobDataMap()));
        prevDetailDataMap = context.getJobDetail().getJobDataMap();

        System.out.println("prevTrigger == context.getTrigger()？" + (prevTrigger == context.getTrigger())); // false
        log.info("Trigger：{}；hashcode：{}", context.getTrigger(), System.identityHashCode(context.getTrigger()));
        prevTrigger = context.getTrigger();

        System.out.println("prevTriggerDataMap == context.getTrigger().getJobDataMap()？" + (prevTriggerDataMap == context.getTrigger().getJobDataMap())); // false
        log.info("triggerDataMap：{}", System.identityHashCode(context.getTrigger().getJobDataMap()));
        prevTriggerDataMap = context.getTrigger().getJobDataMap();

        System.out.println("prevScheduler == context.getScheduler()？" + (prevScheduler == context.getScheduler())); // true
        log.info("Scheduler：{}；hashcode：{}", context.getScheduler(), System.identityHashCode(context.getScheduler()));
        prevScheduler = context.getScheduler();
    }
}
