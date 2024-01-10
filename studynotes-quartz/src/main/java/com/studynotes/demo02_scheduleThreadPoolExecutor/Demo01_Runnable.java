package com.studynotes.demo02_scheduleThreadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 */
@Slf4j
public class Demo01_Runnable implements Runnable {

    String name;
    int count;

    Demo01_Runnable(String name, int count) {
        this.count = count;
        this.name = name;
    }

    @Override
    public void run() {
        log.info("任务 {} 第 {} 次执行", name, ++count);
        try {
            if (count == 2) {
                Thread.sleep(21000);
            }
            log.info("执行完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
