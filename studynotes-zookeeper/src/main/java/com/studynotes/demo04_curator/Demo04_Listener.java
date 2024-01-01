package com.studynotes.demo04_curator;

import lombok.SneakyThrows;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

/**
 * Description: Curator 提供的 Listener 监听器，不同 Listener 监听不同的事件
 */
public class Demo04_Listener {

    private static final CuratorFramework CLIENT = CuratorUtil.CLIENT;

    private static final String PATH = CuratorUtil.PATH;

    @Test
    @SneakyThrows
    void testCuratorListener() {
        // 添加 ConnectionStateListener 监听器
        CLIENT.getConnectionStateListenable().addListener(new MyConnectionStateListener());
        // 添加 CuratorListener 监听器
        CLIENT.getCuratorListenable().addListener(new MyCuratorListener());

        Stat stat1 = CLIENT.setData().forPath(PATH, "new Data1".getBytes());
        System.out.println("stat1：" + stat1);

        // Background 执行完后调用 CuratorListener 回调
        Stat stat2 = CLIENT.setData().inBackground().forPath(PATH, "new Data2".getBytes());
        System.out.println("stat2：" + stat2);

        System.in.read();
    }


    /**
     * Description: 监听 Curator 的 Background 操作
     */
    static class MyCuratorListener implements CuratorListener {
        @Override
        public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
            switch (curatorEvent.getType()) {
                case CREATE:
                    System.out.println("CREATE:" +
                            curatorEvent.getPath());
                    break;
                case DELETE:
                    System.out.println("DELETE:" +
                            curatorEvent.getPath());
                    break;
                case EXISTS:
                    System.out.println("EXISTS:" +
                            curatorEvent.getPath());
                    break;
                case GET_DATA:
                    System.out.println("GET_DATA:" +
                            curatorEvent.getPath() + ","
                            + new String(curatorEvent.getData()));
                    break;
                case SET_DATA:
                    System.out.println("SET_DATA:" +
                            new String(curatorEvent.getData()));
                    break;
                case CHILDREN:
                    System.out.println("CHILDREN:" +
                            curatorEvent.getPath());
                    break;
                default:
            }
        }

    }

    /**
     * Description: 监听 Curator 连接状态
     */
    static class MyConnectionStateListener implements ConnectionStateListener {
        @Override
        public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
            switch (connectionState) {
                // 第一次成功连接到ZooKeeper之后会进入该状态。
                // 对于每个CuratorFramework对象，此状态仅出现一次
                case CONNECTED:
                    break;
                // ZooKeeper的连接丢失
                case SUSPENDED:
                    break;
                // 丢失的连接被重新建立
                case RECONNECTED:
                    break;
                // 当Curator认为会话已经过期时，则进入此状态
                case LOST:
                    break;
                // 连接进入只读模式
                case READ_ONLY:
                    break;
            }
        }
    }
}
