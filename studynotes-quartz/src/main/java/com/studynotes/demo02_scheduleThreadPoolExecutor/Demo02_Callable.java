package com.studynotes.demo02_scheduleThreadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * Description:
 */
@Slf4j
public class Demo02_Callable implements Callable<String> {

    String name;
    int count;

    Demo02_Callable(String name, int count) {
        this.count = count;
        this.name = name;
    }

    @Override
    public String call() {
        log.info("任务 {} 第 {} 次执行", name, ++count);
        try {
            if (count == 2) {
                Thread.sleep(21000);
            }
            log.info("执行完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "我真的做完了";
    }
}
