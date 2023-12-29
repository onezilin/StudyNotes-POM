package com.studynotes.java16_concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo22_Executors {
    public static void main(String[] args) throws Exception {
        CustomTask ct = new CustomTask();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            exec.execute(ct);
        }
        TimeUnit.MILLISECONDS.sleep(1);
        exec.shutdownNow();
    }
}

class CustomTask implements Runnable {
    private int number;

    public synchronized void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + ": " + number);
            Thread.yield();
            ++number;
        }
    }
}
