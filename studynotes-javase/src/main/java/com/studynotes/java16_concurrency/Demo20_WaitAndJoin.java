package com.studynotes.java16_concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

// wait和join区别
public class Demo20_WaitAndJoin {
    public static void main(String[] args) {

    }
}

class Demo extends Thread {

    public static void main(String[] args) throws Exception {
        waitTest(); // for test1
        //        joinTest(); // for test2
    }

    static void waitTest() throws Exception {
        System.out.println("-------------------- Wait Demo01_Reactor --------------------");
        Demo t1 = new Demo("t1");
        show("starts");
        t1.start();

        show("calls t1.wait()");
        synchronized (t1) {
            t1.wait(1);
        }

        show("continues");
    }

    @Override
    public void run() {
        synchronized (this) {
            show("Enter synchronized block");
            sleepms(1000);

            show("calls notify()");
            this.notify();

            sleepms(2000);
            show("Exit synchronized block");
        }
        sleepms(3000);
        show("exits.");
    }

    private void sleepms(int ms) {
        try {
            show("sleeps " + ms + " ms.");
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void joinTest() throws Exception {
        System.out.println("-------------------- Join Demo01_Reactor --------------------");
        Demo t2 = new Demo("t2");
        show("starts");
        t2.start();

        show("call t2.joins()");
        t2.join();

        show("continues");
    }

    public Demo() {
    }

    public Demo(String name) {
        super(name);
    }

    public static void show(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss.SSS]");
        System.out.println(sdf.format(new Date()) + " " + Thread.currentThread().getName() + " " + msg);
    }
}

