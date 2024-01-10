package com.studynotes.demo09_misfire;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import java.util.Date;

/**
 * Description: Misfire 机制的 Job，直接打印执行时间和下次执行时间
 */
@Slf4j
@PersistJobDataAfterExecution
public class Demo01_MisfireJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Job 任务执行，CurrentTime: {}，FireTime: {}, NextTime: {}", new Date(), context.getFireTime(),
                context.getNextFireTime());
    }
}
