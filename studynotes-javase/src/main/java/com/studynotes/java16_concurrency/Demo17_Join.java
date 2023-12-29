package com.studynotes.java16_concurrency;

import org.junit.jupiter.api.Test;

/**
 * Description: 对join方法的理解：
 * * join要在start后执行,否则没有效果,
 * * 阻塞执行到当前行的线程,直到调用的线程执行完毕
 */
public class Demo17_Join {
    @Test
    public void test() {
        System.out.println("主线程开启...");
        Thread thread1 = new Thread(new Plugin1());
        Thread thread2 = new Thread(new Plugin2());
        try {
            //            开始插件1的安装
            thread1.start();
            //            等插件1的安装线程结束
            thread1.join();

            // main线程会在这里阻塞,知道thread1执行完毕后才会继续执行

            //            再开始插件2的安装
            thread2.start();
            //            等插件2的安装线程结束,才能回到主线程
            thread2.join();

            // main线程会在这里阻塞,知道thread1执行完毕后才会继续执行

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束，程序安装完成！");
    }

    class Plugin1 implements Runnable {

        @Override
        public void run() {
            System.out.println("插件1开始安装.");
            System.out.println("安装中...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("插件1完成安装.");
        }
    }

    class Plugin2 implements Runnable {

        @Override
        public void run() {
            System.out.println("插件2开始安装.");
            System.out.println("安装中...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("插件2完成安装.");
        }
    }
}

/**
 * 插件1
 */
