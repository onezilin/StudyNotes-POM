package com.studynotes.demo03_scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Description: 定时任务配置类
 */
@Configuration
// 开启定时任务功能
@EnableScheduling
public class Demo01_MyConfig {

    /**
     * Description: 配置 ScheduledThreadPoolExecutor，设置核心线程数为 16
     */
    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(16);
    }
}
