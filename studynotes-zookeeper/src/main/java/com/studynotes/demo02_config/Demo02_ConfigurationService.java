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
 * Description: 【实现可恢复的 ZooKeeper 应用】
 * 由于上一个例子中异常全部被屏蔽，若抛出异常则可能中断配置的读写
 * 这里捕获 KeeperException 异常，进行重试恢复
 */
public class Demo02_ConfigurationService {

    public static final String PATH = "/config";

    private static final ZooKeeper ZK_CLIENT = ZookeeperUtil.ZK_CLIENT;

    private final ChangedActiveKeyValueStore store = new ChangedActiveKeyValueStore();

    private final Random random = new Random();

    @Test
    void test() {
        while (true) {
            try {
                run();
            } catch (KeeperException.SessionExpiredException e) {
                // start a new session
            } catch (KeeperException e) {
                // already retried ,so exit
                e.printStackTrace();
                break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run() throws InterruptedException, KeeperException {
        while (true) {
            String value = random.nextInt(100) + "";
            store.write(PATH, value);
            System.out.printf("Set %s to %s\n", PATH, value);
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        }
    }

    public static class ChangedActiveKeyValueStore {
        private static final Charset CHARSET = StandardCharsets.UTF_8;
        // 重试次数
        private static final int MAX_RETRIES = 5;
        // 重试间隔时间
        private static final long RETRY_PERIOD_SECONDS = 5;

        public void write(String path, String value) throws KeeperException, InterruptedException {
            int retries = 0;
            while (true) {
                try {
                    Stat stat = ZK_CLIENT.exists(path, false);
                    if (stat == null) {
                        ZK_CLIENT.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                                CreateMode.PERSISTENT);
                    } else {
                        ZK_CLIENT.setData(path, value.getBytes(CHARSET), stat.getVersion());
                    }
                } catch (KeeperException.SessionExpiredException e) {
                    throw e;
                } catch (KeeperException | InterruptedException e) {
                    //
                    if (retries++ == MAX_RETRIES) {
                        throw e;
                    }
                    // sleep then retry
                    TimeUnit.SECONDS.sleep(RETRY_PERIOD_SECONDS);
                }
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
}
