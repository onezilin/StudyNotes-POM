package com.studynotes.demo06_jobDataMap;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Description: Quartz 提供通过 setter 方法注入属性的方式，获取合并后的 JobDataMap 中指定键对应的值
 */
@Slf4j
public class Demo02_DataJob implements Job {

    private String jobDetail;

    private String trigger;

    private String mergeName;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("JobDetail = {}", jobDetail); // JobDetail = jobDataMap测试
        log.info("Trigger = {}", trigger); // Trigger = triggerDataMap测试
        log.info("mergeName = {}", mergeName); // mergeName = triggerName
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public void setMergeName(String mergeName) {
        this.mergeName = mergeName;
    }
}
