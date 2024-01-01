package com.studynotes.demo01_zookeeper;

import lombok.SneakyThrows;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description: ZooKeeper 客户端使用
 */
public class Demo01_ZooKeeper {

    private static final String PATH = "/zk";

    private static final ZooKeeper ZK_CLIENT = ZookeeperUtil.ZK_CLIENT;

    private static final Watcher WATCHER = ZookeeperUtil.WATCHER;

    /**
     * Description: 创建 Zookeeper 节点
     * <p>
     * ZooDefs.Ids.OPEN_ACL_UNSAFE：完全开放的 ACL，任何连接的客户端都可以操作该属性 znode
     * CreateMode.PERSISTENT：持久化节点
     */
    @Test
    @SneakyThrows
    void create() {
        Stat stat = new Stat();
        ZK_CLIENT.create(PATH, "myData".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, stat);
        System.out.println("stat 信息：" + stat);
    }

    @Test
    @SneakyThrows
    void set() {
        Stat stat = ZK_CLIENT.setData(PATH, "myData中国".getBytes(), -1);
        System.out.println("stat 信息：" + stat);
    }

    @Test
    @SneakyThrows
    void get() {
        Stat stat = new Stat();
        byte[] bytes = ZK_CLIENT.getData(PATH, WATCHER, stat);
        System.out.println("数据：" + new String(bytes, 0, bytes.length));
        System.out.println("stat 信息：" + stat);
    }

    @Test
    @SneakyThrows
    void delete() {
        ZK_CLIENT.delete(PATH, -1);
    }

    /**
     * Description: ZooKeeper 客户端没有提供 deleteall 方法，手写一个
     */
    @Test
    @SneakyThrows
    void deleteAll() {
        List<String> pathList = ZK_CLIENT.getChildren(PATH, WATCHER);
        deleteAll(PATH, pathList);
    }

    @SneakyThrows
    private void deleteAll(String parentPath, List<String> pathList) {
        if (CollectionUtils.isEmpty(pathList)) return;

        for (String path : pathList) {
            path = parentPath + "/" + path;
            List<String> nestedPathList = ZK_CLIENT.getChildren(path, WATCHER);
            if (CollectionUtils.isEmpty(nestedPathList)) {
                ZK_CLIENT.delete(path, -1);
            } else {
                deleteAll(path, nestedPathList);
            }
        }
    }

    /**
     * Description: 查看指定节点是否存在，存在则返回 stat 信息；不存在则返回 null。
     */
    @Test
    @SneakyThrows
    void exists() {
        Stat stat = ZK_CLIENT.exists(PATH, WATCHER);
        System.out.println("stat 信息：" + stat);
    }
}
