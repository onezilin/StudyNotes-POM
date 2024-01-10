package com.studynotes.demo11_listener;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.quartz.*;

import java.io.IOException;

/**
 * Description:
 */
public class Demo03_SchedulerListener {

    @Test
    public void test() {
        ListenerUtil.schedule(listenerManager -> {
            listenerManager.addSchedulerListener(new Demo03_SchedulerListener.MySchedulerListener());
        });
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }

    static class MySchedulerListener implements SchedulerListener {

        /**
         * Description: Scheduler 有新的 JobDetail 被添加时调用
         */
        @Override
        public void jobScheduled(Trigger trigger) {

        }

        /**
         * Description: Scheduler 中的 JobDetail 被删除时调用
         */
        @Override
        public void jobUnscheduled(TriggerKey triggerKey) {

        }

        @Override
        public void triggerFinalized(Trigger trigger) {

        }

        @Override
        public void triggerPaused(TriggerKey triggerKey) {

        }

        @Override
        public void triggersPaused(String triggerGroup) {

        }

        @Override
        public void triggerResumed(TriggerKey triggerKey) {

        }

        @Override
        public void triggersResumed(String triggerGroup) {

        }

        @Override
        public void jobAdded(JobDetail jobDetail) {

        }

        @Override
        public void jobDeleted(JobKey jobKey) {

        }

        @Override
        public void jobPaused(JobKey jobKey) {

        }

        @Override
        public void jobsPaused(String jobGroup) {

        }

        @Override
        public void jobResumed(JobKey jobKey) {

        }

        @Override
        public void jobsResumed(String jobGroup) {

        }

        @Override
        public void schedulerError(String msg, SchedulerException cause) {

        }

        @Override
        public void schedulerInStandbyMode() {

        }

        /**
         * Description: Scheduler 被启动后调用
         */
        @Override
        public void schedulerStarted() {
            System.out.println("schedulerStarted");
        }

        /**
         * Description: Scheduler 被启动前调用
         */
        @Override
        public void schedulerStarting() {
            System.out.println("schedulerStarting");
        }

        /**
         * Description: Scheduler 被 shutdown 后调用
         */
        @Override
        public void schedulerShutdown() {
            System.out.println("schedulerShutdown");
        }

        /**
         * Description: Scheduler 被 shutdown 前调用
         */
        @Override
        public void schedulerShuttingdown() {
            System.out.println("schedulerShuttingdown");
        }

        /**
         * Description: Scheduler clear 后调用
         */
        @Override
        public void schedulingDataCleared() {
            System.out.println("schedulingDataCleared");
        }
    }
}
