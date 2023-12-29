package com.studynotes.java16_concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Description: 守护线程的理解：
 * * 线程分为用户线程和守护线程，守护线程主要是在后台提供服务的线程，比如垃圾回收
 * * 程序在所有用户线程结束后就结束，程序也会杀死所有守护线程
 * * 守护线程创建的线程也是守护线程
 */
@Slf4j
public class Demo14_Daemon {
    public static void main(String[] args) {
        Thread d = new Daemon();
        System.out.println("d.isDaemon() = " + d.isDaemon());
        // Allow the daemon threads to finish
        // their startup processes:
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Waiting for CR");
        try {
            // 保证main线程不死
            stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // main线程结束，所有守护线程死亡
    }

    @Test
    void test() {

        System.out.println("我是主线程");

        Thread daemon = new Thread(() -> {
            log.info("我是守护线程");
        });
        daemon.setDaemon(true);
        daemon.start();

        // 保证main线程不死
        new Scanner(System.in).nextInt();
    }
}

class Daemon extends Thread {
    private static final int SIZE = 10;
    private Thread[] t = new Thread[SIZE];

    public Daemon() {
        setDaemon(true);
        start();
    }

    public void run() {
        for (int i = 0; i < SIZE; i++)
            t[i] = new DaemonSpawn(i);
        for (int i = 0; i < SIZE; i++)
            System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon());
        while (true)
            yield();
    }
}

// 守护线程生成的线程也是守护线程
class DaemonSpawn extends Thread {
    public DaemonSpawn(int i) {
        System.out.println("DaemonSpawn " + i + " started");
        start();
    }

    public void run() {
        while (true)
            yield();
    }

}
