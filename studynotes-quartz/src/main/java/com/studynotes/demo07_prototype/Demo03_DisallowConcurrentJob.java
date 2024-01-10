package com.studynotes.demo07_prototype;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * Description: @DisallowConcurrentExecution 注解不允许并发执行某个 Job 实例，即同一个 Job 任务都会等待上一个任务执行完毕后，才会执行。
 */
@Slf4j
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class Demo03_DisallowConcurrentJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap detailMap = context.getJobDetail().getJobDataMap();
        int count = detailMap.getInt("count");
        count++;
        detailMap.put("count", count);
        log.info("Job 任务 {} 第 {} 次执行", this, count);
        try {
            if (count == 2) {
                Thread.sleep(5000);
            }
            log.info("执行完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
