package com.studynotes.demo06_jobDataMap;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.util.stream.Collectors;

/**
 * Description: JobDataMap 用于在 Job 实例、JobDetail、Trigger 之间传递数据
 */
@Slf4j
public class Demo01_DataJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("JobDetail dataMap: {}", getString(context.getJobDetail().getJobDataMap()));
        log.info("Trigger dataMap: {}", getString(context.getTrigger().getJobDataMap()));

        // 获取合并后的 JobDataMap，Trigger 覆盖 JobDetail 中 JobDataMap 相同的键
        log.info("获取合并后的 JobDataMap: {}", getString(context.getMergedJobDataMap()));
    }

    public String getString(JobDataMap jobDataMap) {
        if (jobDataMap == null || jobDataMap.isEmpty()) {
            return "JobDataMap is null or empty";
        }
        return jobDataMap.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining(","));
    }
}
