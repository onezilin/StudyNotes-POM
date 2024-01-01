package com.studynotes.demo04_curator;

import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

/**
 * Description: Curator 提供 Background 异步操作
 */
public class Demo03_Background {

    private static final CuratorFramework CLIENT = CuratorUtil.CLIENT;

    private static final String PATH = CuratorUtil.PATH;

    @Test
    @SneakyThrows
    void test() {
        // 异步操作直接返回，stat 为 null，若想获取值，则需要添加监听器
        Stat stat = CLIENT.setData().inBackground().forPath(PATH, "new Data".getBytes());
        System.out.println(stat);
    }
}
