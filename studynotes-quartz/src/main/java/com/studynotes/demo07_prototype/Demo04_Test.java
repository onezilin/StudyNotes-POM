package com.studynotes.demo07_prototype;

import com.studynotes.demo04_quartz.QuartzUtil;
import org.junit.jupiter.api.Test;
import org.quartz.JobDataMap;

/**
 * Description:
 */
public class Demo04_Test {

    /**
     * Description: 测试 Job 实例、JobDetail（及 JobDataMap）、Trigger（及 JobDataMap）都是多例的
     * 测试步骤：
     * 1、运行测试方法，查看控制台输出
     * <p>
     * 预期结果：
     * 1、每次 Job 任务中的 Job 实例、JobDetail（及 JobDataMap）、Trigger（及 JobDataMap）的 hashCode 都不一样
     * <p>
     * 结论：
     * 1、Job 实例、JobDetail（及 JobDataMap）、Trigger（及 JobDataMap）都是多例的
     */
    @Test
    public void test1() {
        JobDataMap detailDataMap = new JobDataMap();
        System.out.println("detailDataMap：" + System.identityHashCode(detailDataMap));
        JobDataMap triggerDataMap = new JobDataMap();
        System.out.println("triggerDataMap：" + System.identityHashCode(triggerDataMap));

        QuartzUtil.quartzSchedule(Demo01_PrototypeJob.class, detailDataMap, triggerDataMap);
    }

    /**
     * Description: 测试 @PersistJobDataAfterExecution 注解作用
     * 测试步骤：
     * 1、运行测试方法，查看控制台 count 的输出
     * 2、注释掉 Demo02_PersistDataJob 类上的 @PersistJobDataAfterExecution 注解，运行测试方法，查看控制台 count 的输出
     * <p>
     * 预期结果：
     * 1、每次运行测试方法，count 的值都会递增
     * 2、每次运行测试方法，count 的值都是 1
     * <p>
     * 结论：
     * 注解 @PersistJobDataAfterExecution 作用是：在每次执行完 Job 任务后，都会更新 JobDetail 中 JobDataMap 的数据
     */
    @Test
    public void test2() {
        JobDataMap detailDataMap = new JobDataMap();
        detailDataMap.put("count", 0);
        QuartzUtil.quartzSchedule(Demo02_PersistDataJob.class, detailDataMap, new JobDataMap());
    }

    /**
     * Description: 测试 @DisallowConcurrentExecution 注解作用
     * 测试步骤：
     * 1、运行测试方法，查看控制台 count 的输出
     * 2、注释掉 Demo03_DisallowConcurrentJob 类上的 @DisallowConcurrentExecution 注解，运行测试方法，查看控制台 count 的输出
     * <p>
     * 预期结果：
     * 1、每个任务都会等待上一个任务执行完毕后，才会执行；
     * 例如：第二个任务阻塞了 5 秒，那么第三个任务会在第二个任务执行完毕后，再执行，后续任务执行方式和 atFiexedRate() 方法的效果一样
     * 2、每个任务都会按计划执行，不会等待上一个任务执行完毕后，才会执行
     * <p>
     * 结论：
     * 注解 @DisallowConcurrentExecution 作用是：每个任务都会等待上一个任务执行完毕后，才会执行
     */
    @Test
    public void test3() {
        JobDataMap detailDataMap = new JobDataMap();
        detailDataMap.put("count", 0);
        QuartzUtil.quartzSchedule(Demo03_DisallowConcurrentJob.class, detailDataMap, new JobDataMap());
    }
}
