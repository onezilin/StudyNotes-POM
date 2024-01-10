package com.studynotes.demo12_spring;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 自定义 Job 任务，在配置文件中指定方法名
 * <p>
 * 不推荐使用这种方式，因为这种方式不支持持久化，例如存储到数据库中
 */
@Slf4j
public class MyJobMethodInvoke {

    public void executeInternal() {
        log.info("MyJobMethodInvoke 任务 {} 执行", this);
    }
}
