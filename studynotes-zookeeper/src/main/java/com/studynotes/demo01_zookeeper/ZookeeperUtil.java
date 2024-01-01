package com.studynotes.demo01_zookeeper;

import com.studynotes.constant.CommonConstants;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Description: ZooKeeper 工具类，获取 ZooKeeper 客户端
 */
public class ZookeeperUtil {

    public static final ZooKeeper ZK_CLIENT;

    public static final Watcher WATCHER = new MyWatcher();

    private static final int TIMEOUT = 30000;

    static {
        // 添加 Watcher 监听器
        Watcher watcher = new MyWatcher();
        try {
            ZK_CLIENT = new ZooKeeper(CommonConstants.ZOOKEEPER_HOST, TIMEOUT, watcher);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class MyWatcher implements Watcher {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("监听器：" + watchedEvent.toString());
        }
    }
}
