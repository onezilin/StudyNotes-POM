package com.studynotes.demo04_curator;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Description: Curator 提供 InterProcessMutex 互斥锁用于实现分布式锁
 */
public class Demo08_ZookeeperLock {

    private static final String ZK_LOCK_PATH = "/curator/lock";

    @Test
    public void zookeeperLockTest() {
        Thread t1 = new Thread(this::doWithLock, "t1");
        Thread t2 = new Thread(this::doWithLock, "t2");

        t1.start();
        t2.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWithLock() {
        String threadName = Thread.currentThread().getName();
        InterProcessMutex lock = new InterProcessMutex(CuratorUtil.CLIENT, ZK_LOCK_PATH);
        System.out.println(threadName + " is running");
        try {
            lock.acquire();
            System.out.println(threadName + " hold lock");
            Thread.sleep(5000L);
            System.out.println(threadName + " release lock");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
