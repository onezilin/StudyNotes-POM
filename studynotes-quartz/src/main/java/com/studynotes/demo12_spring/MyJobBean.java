package com.studynotes.demo12_spring;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Description: 自定义 Job 任务，继承 Spring 下的 QuartzJobBean 类
 */
@Slf4j
public class MyJobBean extends QuartzJobBean {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void executeInternal(@NonNull JobExecutionContext jobExecutionContext) {
        log.info("MyJobBean${} 任务 {} 第 {} 次执行", jobExecutionContext.getJobDetail().getKey(), this, ++count);
    }
}
