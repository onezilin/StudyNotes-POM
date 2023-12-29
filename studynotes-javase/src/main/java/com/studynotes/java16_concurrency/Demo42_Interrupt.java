package com.studynotes.java16_concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * Description: 测试 interrupt()方法对 WAITING 和 TIME_WAITING 状态线程的中断作用
 */
@Slf4j
public class Demo42_Interrupt {


    public static void main(String[] args) {

    }

    @Test
    public static void waitTest() {
        Object o = new Object();
        Thread t = new Thread(() -> {
            log.info("子线程启动");
            synchronized (o) {
                try {
                    log.info("子线程 wait");
                    o.wait();
                } catch (InterruptedException e) {
                    // InterruptedException 异常被捕获后，InterruptStatus 置为 false
                    log.info("子线程中断，中断状态：{}", Thread.currentThread().isInterrupted()); // false
                }
            }
            log.info("子线程执行完成");
        });
        t.start();

        new Scanner(System.in).nextInt();

        log.info("子线程中断，中断状态：{}", t.isInterrupted()); // false
        t.interrupt();
        log.info("子线程中断，中断状态：{}", t.isInterrupted()); // true
    }

    public static void isInterrupted() {
        Object o = new Object();
        Thread t = new Thread(() -> {
            log.info("子线程启动");

            while (!Thread.currentThread().isInterrupted()) {
                log.info("睡一下，做一下");
                Thread.yield();
            }

            log.info("子线程中断，中断状态：{}", Thread.currentThread().isInterrupted());
            log.info("子线程执行完成");
        });
        t.start();


        log.info("子线程中断，中断状态：{}", t.isInterrupted());
        try {
            Thread.sleep(500);
            t.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("子线程中断，中断状态：{}", t.isInterrupted());
    }
}
