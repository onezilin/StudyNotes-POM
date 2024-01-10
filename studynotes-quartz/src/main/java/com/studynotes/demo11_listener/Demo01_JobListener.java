package com.studynotes.demo11_listener;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.impl.matchers.KeyMatcher;

import java.io.IOException;

/**
 * Description: 实现 JobListener 接口，监听 Job 的执行
 */
public class Demo01_JobListener {

    /**
     * Description: 添加全局 JobListener
     */
    @Test
    public void test1() {

        ListenerUtil.schedule(listenerManager -> {
            listenerManager.addJobListener(new Demo01_JobListener.MyJobListener());
        });
    }

    /**
     * Description: 添加局部 JobListener
     * <p>
     * 测试步骤：
     * 1、测试 name = "myJob" 时，监听器能否监听到 Job 的执行
     * 2、测试 name = "myJob1" 时，监听器能否监听到 Job 的执行
     * <p>
     * 预期结果：
     * 1、监听器能够监听到 Job 的执行
     * 2、监听器不能够监听到 Job 的执行
     * <p>
     * 结论：
     * JobListener 只能监听到指定 JobKey 的 Job 的执行
     */
    @Test
    public void test2() {
        String name = "myJob";
        // String name = "myJob1";

        ListenerUtil.schedule(listenerManager -> {
            listenerManager.addJobListener(new Demo01_JobListener.MyJobListener(),
                    // 监听指定 JobKey 的 Job
                    KeyMatcher.keyEquals(new JobKey(name, "jobGroup")));
        });
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }

    /**
     * Description: 自定义 JobListener
     */
    static class MyJobListener implements JobListener {
        /**
         * Description: 获取 JobListener 的名称
         */
        @Override
        public String getName() {
            return "MyJobListener";
        }

        /**
         * Description: Job 执行之前调用
         */
        @Override
        public void jobToBeExecuted(JobExecutionContext context) {
            String jobName = context.getJobDetail().getKey().getName();
            System.out.println(jobName + " 即将执行");
        }

        /**
         * Description: Job 将要执行，但是被 TriggerListener 否决了时调用
         */
        @Override
        public void jobExecutionVetoed(JobExecutionContext context) {
            String jobName = context.getJobDetail().getKey().getName();
            System.out.println(jobName + " 被 TriggerListener 否决了");
        }

        /**
         * Description: Job 执行之后调用
         */
        @Override
        public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
            String jobName = context.getJobDetail().getKey().getName();
            System.out.println(jobName + " 执行完毕");
        }
    }
}
