package com.studynotes.demo05_jobExecutionContext;

import com.studynotes.demo04_quartz.QuartzUtil;
import org.junit.jupiter.api.Test;

/**
 * Description: 测试 JobExecutionContext
 */
public class Demo02_Test {

    /**
     * Description:
     * 测试步骤：
     * 1、启动后查看 JobExecutionContext 输出结果
     */
    @Test
    public void test() {
        QuartzUtil.quartzSchedule(Demo01_ContextJob.class);
    }
}
