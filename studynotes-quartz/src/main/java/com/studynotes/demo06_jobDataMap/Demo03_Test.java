package com.studynotes.demo06_jobDataMap;

import com.studynotes.demo04_quartz.QuartzUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.JobDataMap;

/**
 * Description: 测试 JobDataMap。JobDetai 和 Trigger 向 Job 实例传递参数
 */
@Slf4j
public class Demo03_Test {

    private static JobDataMap detailDataMap = new JobDataMap();

    private static JobDataMap triggerDataMap = new JobDataMap();

    static {
        detailDataMap.put("jobDetail", "jobDataMap测试");
        detailDataMap.put("mergeName", "jobName");

        triggerDataMap.put("trigger", "triggerDataMap测试");
        triggerDataMap.put("mergeName", "triggerName");
    }

    /**
     * Description: 测试 JobDetai 和 Trigger 中的 JobDataMap，及合并后的 JobDataMap
     */
    @Test
    public void test1() {
        QuartzUtil.quartzSchedule(Demo01_DataJob.class, detailDataMap, triggerDataMap);
    }

    /**
     * Description: 通过 setter 方法注入属性的方式，获取合并后的 JobDataMap 中指定键对应的值
     */
    @Test
    public void test2() {
        QuartzUtil.quartzSchedule(Demo02_DataJob.class, detailDataMap, triggerDataMap);
    }
}
