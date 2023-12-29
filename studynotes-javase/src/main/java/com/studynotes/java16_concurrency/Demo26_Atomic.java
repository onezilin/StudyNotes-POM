package com.studynotes.java16_concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

// 使用AtomicInteger解决volatile不能保证原子性的问题
public class Demo26_Atomic {
    public static AtomicInteger num = new AtomicInteger(0);

    public static void increase() {
        num.incrementAndGet();
    }

    private static final int THREAD_COUNT = 30;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(num);
    }

    @Test
    void fieldUpdater() {
        AtomicIntegerFieldUpdater<Demo26_Atomic> updater1 = AtomicIntegerFieldUpdater.newUpdater(Demo26_Atomic.class,
                "outerIntVal");
        updater1.compareAndSet(this, 0, 1);
        System.out.println(updater1.get(this));

        AtomicIntegerFieldUpdater<TestClass> updater2 = AtomicIntegerFieldUpdater.newUpdater(TestClass.class,
                "innerIntVal");
        TestClass testClass = new TestClass();
        updater2.compareAndSet(testClass, 0, 1);
        System.out.println(updater2.get(testClass));
    }

    // 需要被 volatile 修饰
    // private int outerIntVal;
    private volatile int outerIntVal;

    static class TestClass {
        // 需要能被直接访问
        // private volatile int innerIntVal;
        volatile int innerIntVal;
    }
}


