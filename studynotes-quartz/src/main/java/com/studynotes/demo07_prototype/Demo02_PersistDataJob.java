package com.studynotes.demo07_prototype;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

/**
 * Description: @PersistJobDataAfterExecution 注解，在每次执行完 Job 任务后，都会更新 JobDetail 中 JobDataMap 的数据，
 * 这样就可以在 Job 实例之间传递数据了。
 */
@PersistJobDataAfterExecution
@Slf4j
public class Demo02_PersistDataJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap detailDataMap = context.getJobDetail().getJobDataMap();
        detailDataMap.put("count", detailDataMap.getInt("count") + 1);
        System.out.println("count：" + detailDataMap.getInt("count"));
    }
}
