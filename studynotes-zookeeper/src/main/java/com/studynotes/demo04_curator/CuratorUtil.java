package com.studynotes.demo04_curator;

import com.studynotes.constant.CommonConstants;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Description:
 */
public class CuratorUtil {

    public static final CuratorFramework CLIENT;

    public static final String PATH = "/curator";

    private static final int TIMEOUT = 30000;

    static {
        // 重试策略。连接不上ZooKeeper集群会重试三次，间隔会递增
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(TIMEOUT, 3);
        // 创建Curator客户端，连接ZooKeeper
        CLIENT = CuratorFrameworkFactory.newClient(CommonConstants.ZOOKEEPER_HOST, retryPolicy);
        CLIENT.start();
    }
}
