package com.studynotes.demo01_timer;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * Description: 继承 TimerTask，用于实现定时任务业务
 */
@Slf4j
public class Demo01_TimerTask extends TimerTask {
    String name;
    int count;

    Demo01_TimerTask(String name, int count) {
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
