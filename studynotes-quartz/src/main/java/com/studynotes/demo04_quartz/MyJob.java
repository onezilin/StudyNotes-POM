package com.studynotes.demo04_quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Description: 自定义 Job 任务，实现 Job 接口，重写 execute 方法
 */
@Slf4j
public class MyJob implements Job {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("Job 任务 {} 第 {} 次执行", this, ++count);
    }
}
