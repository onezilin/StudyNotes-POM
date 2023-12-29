package com.studynotes.java16_concurrency;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Description: 测试调用父类同步方法时会不会产生锁竞争
 */
public class Demo45_SuperLock {


    static class Father {
        public synchronized void printA() {
            System.out.println("father printA, sleep 3s");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AAAAAAAAAAAAAAAAA");
        }
    }

    static class Son extends Father {
        public void printB() {
            // 同一个对象调用父类的同步方法，会产生锁竞争
            super.printA();
            synchronized (this) {
                System.out.println("son printB, sleep 3s");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("BBBBBBBBBBBBBBBBB");
            }
        }
    }

    @Test
    public void test() throws IOException {
        Son son = new Son();
        new Thread(son::printB).start();
        new Thread(son::printB).start();

        System.in.read();
    }
}
