package com.studynotes.demo04_curator;

import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.WatchedEvent;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Description: 在 Curator 客户端中，我们可以使用 usingWatcher() 方法添加 Watcher
 * 能够添加 Watcher 的有 checkExists()、getData()以及 getChildren() 三个方法
 */
public class Demo05_Watcher {

    private static final CuratorFramework CLIENT = CuratorUtil.CLIENT;

    private static final String PATH = CuratorUtil.PATH;

    @Test
    @SneakyThrows
    void test() {
        List<String> children = CLIENT.getChildren().usingWatcher(new MyWatcher()).forPath(PATH);
        System.out.println(children);

        CLIENT.create().forPath(PATH + "/test");
        System.in.read();
    }

    @Test
    @SneakyThrows
    void test1() {
        // 添加永久监听器
        CLIENT.watchers().add().withMode(AddWatchMode.PERSISTENT).usingWatcher(new MyWatcher()).forPath(PATH +
                "/test1");
        CLIENT.create().forPath(PATH + "/test1");
    }

    /**
     * Description: Watcher 只触发一次
     */
    static class MyWatcher implements CuratorWatcher {

        @Override
        public void process(WatchedEvent event) throws Exception {
            System.out.println(event.getType() + "," + event.getPath());
        }
    }
}
