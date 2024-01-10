package com.studynotes.demo11_listener;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;
import org.quartz.impl.matchers.KeyMatcher;

import java.io.IOException;

/**
 * Description: 实现 TriggerListener 接口，监听 Trigger 的执行
 */
public class Demo02_TriggerListener {

    /**
     * Description: 添加全局 TriggerListener
     * <p>
     * 测试步骤：
     * 1、测试 shouldSkip = true 时，Job 是否能够被跳过
     * 2、测试 shouldSkip = false 时，Job 是否能够被跳过
     * <p>
     * 预期结果：
     * 1、Job 能够被跳过
     * 2、Job 不能够被跳过，正常执行
     * <p>
     * 结论：
     * vetoJobExecution() 返回 true 时，Job 被跳过
     */
    @Test
    public void test1() {
        boolean shouldSkip = true;
        // boolean shouldSkip = false;

        ListenerUtil.schedule(listenerManager -> {
            listenerManager.addJobListener(new Demo01_JobListener.MyJobListener());
            listenerManager.addTriggerListener(new Demo02_TriggerListener.MyTriggerListener(shouldSkip));
        });
    }

    /**
     * Description: 添加局部 TriggerListener
     * <p>
     * 测试步骤：
     * 1、测试 name = "myTrigger" 时，监听器能否监听到 Trigger 的执行
     * 2、测试 name = "myTrigger1" 时，监听器能否监听到 Trigger 的执行
     * <p>
     * 预期结果：
     * 1、监听器能够监听到 Trigger 的执行
     * 2、监听器不能够监听到 Trigger 的执行
     * <p>
     * 结论：
     * TriggerListener 只能监听到指定 TriggerKey 的 Trigger 的执行
     */
    @Test
    public void test2() {
        String name = "myTrigger";
        // String name = "myTrigger1";

        ListenerUtil.schedule(listenerManager -> {
            listenerManager.addTriggerListener(new Demo02_TriggerListener.MyTriggerListener(true),
                    // 监听指定 TriggerKey 的 Trigger
                    KeyMatcher.keyEquals(new TriggerKey(name, "triggerGroup")));
        });
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }

    static class MyTriggerListener implements TriggerListener {

        private boolean shouldSkip;

        public MyTriggerListener(boolean shouldSkip) {
            this.shouldSkip = shouldSkip;
        }

        /**
         * Description: 获取 TriggerListener 的名称
         */
        @Override
        public String getName() {
            return "myTriggerListener";
        }

        /**
         * Description: 与该 TriggerListener 相关的 Trigger 被触发，对应 Job 的 execute() 方法执行之前调用
         */
        @Override
        public void triggerFired(Trigger trigger, JobExecutionContext context) {
            String name = trigger.getKey().getName();
            System.out.println(name + " 被触发" + "Job execute() 还未执行");
        }

        /**
         * Description: 与该 TriggerListener 相关的 Trigger 被触发，对应 Job 的 execute() 方法执行之前调用，
         * 该方法返回 true，Job 的 execute() 方法不会被执行
         */
        @Override
        public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
            System.out.println("vetoJobExecution() 被调用，是否跳过 Job 的执行：" + shouldSkip);
            return shouldSkip;
        }

        /**
         * Description: 与该 TriggerListener 相关的 Trigger misfire 时调用
         */
        @Override
        public void triggerMisfired(Trigger trigger) {
            String name = trigger.getKey().getName();
            System.out.println(name + " misfire");
        }

        /**
         * Description: 与该 TriggerListener 相关的 Trigger 被触发，对应 Job 的 execute() 方法执行之后调用
         */
        @Override
        public void triggerComplete(Trigger trigger, JobExecutionContext context,
                                    Trigger.CompletedExecutionInstruction triggerInstructionCode) {
            String name = trigger.getKey().getName();
            System.out.println(name + " 被触发" + "Job execute() 执行完毕");
        }
    }
}
