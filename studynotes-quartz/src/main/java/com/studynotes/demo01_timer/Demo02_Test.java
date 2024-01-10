package com.studynotes.demo01_timer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Description: Timer 定时器执行定时任务
 */
public class Demo02_Test {

    // 注意：new的时候，线程就已经开始执行了
    private Timer t = new Timer();
    private int count = 0;

    @Test
    void test1() {
        for (int i = 0; i < 1; i++) {
            TimerTask task = new Demo01_TimerTask(String.valueOf(i), count);
            // 从现在起执行，直到定时任务执行完后，若任务执行时间超过period，则执行完任务后隔period执行下一次定时任务
            t.schedule(task, new Date(), 10000);
        }
    }

    @Test
    void test2() {
        for (int i = 0; i < 1; i++) {
            TimerTask task = new Demo01_TimerTask(String.valueOf(i), count);
            // 从现在起执行，直到定时任务执行完后，若执行任务期间错过了几次定时任务，会立马连续执行错过的这几次定时任务
            t.scheduleAtFixedRate(task, new Date(), 10000);
        }
    }

    @AfterEach
    void afterEach() throws IOException {
        System.in.read();
    }
}
