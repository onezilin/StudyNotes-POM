package com.studynotes.demo08_trigger;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Description:
 */
@Slf4j
public class Demo02_PriorityJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("{} 优先级：{}", context.getTrigger().getKey().getName(), context.getTrigger().getPriority());
    }
}
