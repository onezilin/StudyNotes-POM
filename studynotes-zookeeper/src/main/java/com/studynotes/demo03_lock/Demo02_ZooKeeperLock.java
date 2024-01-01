package com.studynotes.demo03_lock;

import com.studynotes.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 使用 ZooKeeper 实现分布式公平锁和非公平锁
 */
@Slf4j
public class Demo02_ZooKeeperLock implements Demo01_Lock {

    private final Sync sync;

    private final String path;

    public Demo02_ZooKeeperLock(String path) {
        this(path, false);
    }

    public Demo02_ZooKeeperLock(String path, boolean isFair) {
        this.path = path;
        this.sync = isFair ? new FairSync() : new NonfairSync();
    }

    @Override
    public void lock() throws Exception {
        sync.lock();
    }

    @Override
    public void unlock() {
        sync.unlock();
    }

    abstract class Sync {

        private static final int TIMEOUT = 30000;

        protected static final String ROOT = "/lock";

        protected String locked_path; // 当前 Znode path 路径

        protected ZooKeeper zkClient;

        // 用来同步等待 zkclient 链接到了服务端
        private CountDownLatch connectedSignal = new CountDownLatch(1);

        // 记录是否锁被占有，及锁重入的次数
        protected final AtomicInteger lockCount = new AtomicInteger(0);

        protected Thread thread;

        protected ReentrantLock lock;

        public Sync() {
            this.locked_path = ROOT + path;
            this.lock = new ReentrantLock();
            try {
                zkClient = new ZooKeeper(CommonConstants.ZOOKEEPER_HOST, TIMEOUT, (event) -> {
                    // 建立连接
                    if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                        connectedSignal.countDown();
                    }
                });
                // 等待连接建立完毕
                connectedSignal.await();
                Stat stat = zkClient.exists(ROOT, false);
                if (null == stat) {
                    // 创建根节点
                    zkClient.create(ROOT, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        abstract void lock() throws InterruptedException;

        protected boolean tryAcquire() {
            // 正常的获取锁流程
            lock.lock();
            boolean flag = false;
            if (lockCount.get() == 0) {
                thread = Thread.currentThread();
                lockCount.incrementAndGet();
                flag = true;
            } else if (thread.equals(Thread.currentThread())) {
                lockCount.incrementAndGet();
                flag = true;
            }
            lock.unlock();
            return flag;
        }

        protected void await(String path) throws Exception {
            if (null == path) {
                throw new Exception("path error");
            }
            final CountDownLatch latch = new CountDownLatch(1);

            zkClient.getData(path, (watchedEvent) -> {
                System.out.println("监听到的变化 watchedEvent = " + watchedEvent);
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                    log.info("[WatchedEvent]节点删除");
                    latch.countDown();
                }
            }, null);

            // 等待，直到 path 节点被删除，触发 watcher 唤醒
            latch.await();
        }

        void unlock() {
            // 只有加锁的线程，能够解锁
            if (!thread.equals(Thread.currentThread())) {
                return;
            }
            int newLockCount = lockCount.decrementAndGet();
            if (newLockCount < 0) {
                throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + locked_path);
            }
            if (newLockCount != 0) {
                return;
            }
            try {
                Stat stat = zkClient.exists(locked_path, false);
                if (stat != null) {
                    zkClient.delete(locked_path, -1);
                }
            } catch (KeeperException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Description: <a href="https://www.cnblogs.com/crazymakercircle/p/14504520.html">公平锁</a>
     * 会按照顺序在 Znode 下创建临时顺序节点
     */
    private class FairSync extends Sync {

        protected String prior_path; // 当前 Znode 有序节点的前一个 Znode path

        protected String locked_short_path; // 也就是 Znode 名称

        @Override
        void lock() throws InterruptedException {
            if (!tryAcquire()) {
                return;
            }

            try {
                // 在 ZooKeeper 上加临时节点
                boolean locked = tryLock();
                if (locked) {
                    return;
                }
                // 如果加锁失败就去等待
                while (!locked) {
                    await(prior_path);
                    // 继续查看自己是否是第一个节点
                    List<String> waiters = zkClient.getChildren(ROOT, false);
                    locked_short_path = getShortPath(locked_path);
                    if (checkLocked(waiters)) {
                        locked = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                unlock();
            }
        }

        /**
         * Description: 尝试加锁，在 Znode 上添加临时顺序节点
         */
        private boolean tryLock() throws Exception {
            // 创建临时节点
            locked_path = zkClient.create(locked_path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            if (null == locked_path) {
                throw new Exception("zk error");
            }
            List<String> waiters = zkClient.getChildren(ROOT, false);
            locked_short_path = getShortPath(locked_path);

            if (checkLocked(waiters)) {
                return true;
            }

            // 网络抖动，获取到的子节点列表里可能已经没有自己了，需重新创建临时有序节点
            int index = Collections.binarySearch(waiters, locked_short_path);
            if (index < 0) {
                throw new Exception("节点没有找到: " + locked_short_path);
            }

            // 如果自己没有获得锁，则要监听前一个节点
            prior_path = ROOT + "/" + waiters.get(index - 1);

            return false;
        }

        /**
         * Description: 通过 path 路径获取 Znode 节点名称
         *
         * @param locked_path path 路径
         * @return java.lang.String
         */
        private String getShortPath(String locked_path) {
            int index = locked_path.lastIndexOf(ROOT + "/");
            if (index >= 0) {
                index += ROOT.length() + 1;
                return index <= locked_path.length() ? locked_path.substring(index) : "";
            }
            return null;
        }

        /**
         * Description: 判断当前临时有序节点是否是第一个，如果是第一个，则获取锁
         *
         * @param waiters 所有临时有序节点
         * @return boolean
         */
        private boolean checkLocked(List<String> waiters) {
            // 节点按照编号，升序排列
            Collections.sort(waiters);

            // 如果是第一个，代表自己已经获得了锁
            if (locked_short_path.equals(waiters.get(0))) {
                log.info("成功的获取分布式锁,节点为{}", locked_short_path);
                return true;
            }
            return false;
        }
    }

    /**
     * Description: 非公平锁：会创建 Znode 临时节点
     */
    private class NonfairSync extends Sync {

        @Override
        void lock() throws InterruptedException {
            if (!tryAcquire()) {
                return;
            }
            try {
                // 在 ZooKeeper 上加临时节点
                while (!tryLock()) {
                    // 如果加锁失败就去等待
                    await(locked_path);
                }
            } catch (Exception e) {
                e.printStackTrace();
                unlock();
            }
        }

        /**
         * Description: 添加临时节点，若有则返回 false
         *
         * @return boolean 是否创建 Znode 成功
         */
        private boolean tryLock() throws Exception {
            // 创建临时节点
            Stat stat = zkClient.exists(locked_path, false);
            if (stat == null) {
                locked_path = zkClient.create(locked_path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                if (null == locked_path) {
                    throw new Exception("zk error");
                }
                return true;
            }
            return false;
        }
    }
}
