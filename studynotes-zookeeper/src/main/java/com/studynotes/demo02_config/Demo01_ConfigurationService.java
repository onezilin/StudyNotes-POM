package com.studynotes.demo02_config;

import com.studynotes.demo01_zookeeper.ZookeeperUtil;
import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description: <a href="https://www.cnblogs.com/crazylqy/p/7131364.html">ZooKeeper 应用</a>
 * 使用 ZooKeeper 存储配置信息，实现**高可用性**的配置服务器
 * 先开启 run1() 写数据，后开启 run2() 读数据并监听节点
 */
public class Demo01_ConfigurationService {

    public static final String PATH = "/config";

    private static final ZooKeeper ZK_CLIENT = ZookeeperUtil.ZK_CLIENT;

    /**
     * Description: 更新 Znode 节点随机值
     */
    @Test
    @SneakyThrows
    public void run1() {
        ActiveKeyValueStore store = new ActiveKeyValueStore();
        Random random = new Random();
        while (true) {
            String value = random.nextInt(100) + "";
            store.write(PATH, value);
            System.out.println("Set " + PATH + " to " + value + "\n");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * Description: 用于实现 Znode 节点的读写操作
     */
    public static class ActiveKeyValueStore {
        private static final Charset CHARSET = StandardCharsets.UTF_8;

        @SneakyThrows
        public void write(String path, String value) {
            Stat stat = ZK_CLIENT.exists(path, false);
            if (stat == null) {
                ZK_CLIENT.create(path, value.getBytes(CHARSET),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } else {
                ZK_CLIENT.setData(path, value.getBytes(CHARSET), -1);
            }
        }

        @SneakyThrows
        public String read(String path, Watcher watch) {
            byte[] data = new byte[0];
            Stat stat = ZK_CLIENT.exists(path, false);
            if (stat != null) {
                data = ZK_CLIENT.getData(path, watch, null);
            }
            return new String(data, CHARSET);
        }
    }

    /**
     * Description: 监听器
     */
    public static class ConfigWatcher implements Watcher {

        ActiveKeyValueStore store = new ActiveKeyValueStore();

        /**
         * Description: 实现循环监听
         */
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDataChanged) {
                try {
                    dispalyConfig();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted. exiting. ");
                    Thread.currentThread().interrupt();
                } catch (KeeperException e) {
                    System.out.printf("KeeperException Exiting.\n", e);
                }
            }
        }

        /**
         * Description: 获取值，同时添加监听器
         */
        public void dispalyConfig() throws KeeperException, InterruptedException {
            String value = store.read(PATH, this);
            System.out.printf("Read %s as %s\n", PATH, value);
        }

        @Test
        @SneakyThrows
        void run2() {
            this.dispalyConfig();

            System.in.read();
        }
    }
}
