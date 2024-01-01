package com.studynotes.demo04_curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Description: 由于 Watcher 只能触发一次，Curator提供 Cache 对事件监听进行包装，可以自动处理反复注册监听，简化代码
 */
@Slf4j
public class Demo06_Cache {

    private static final CuratorFramework CLIENT = CuratorUtil.CLIENT;

    private static final String PATH = CuratorUtil.PATH;

    /**
     * Description: 对指定节点的【增删改】操作进行监听
     * 注意：该节点不存在时也可以添加nodeCache；
     */
    @Test
    public void nodeCacheTest() throws Exception {
        NodeCache nodeCache = new NodeCache(CLIENT, PATH);

        // 启动缓存，默认为false。设置为true时，NodeCache在第一次启动时立刻从Zookeeper上读取对应节点的数据，并保存在NodeCache中
        nodeCache.start(true);

        // 返回当前节点的视图（可以理解将ZNode的数据及部分信息封装成ChildData对象），节点不存在时为null
        ChildData childData = nodeCache.getCurrentData();
        if (childData != null) {
            log.info("当前节点：{}，数据{}", childData.getPath(), new String(childData.getData()));
        }

        // 添加监听器（此监听器仅监听状态改变，没有表明是哪个状态改变）

        nodeCache.getListenable().addListener(() -> {
            String data = new String(nodeCache.getCurrentData().getData());
            System.out.println("NodeCache节点路径：" + nodeCache.getCurrentData().getPath()
                    + "，节点数据为：" + data);
        });
    }

    /**
     * Description: 对指定节点的一级子节点的【增删】进行监听
     */
    @Test
    public void pathChildrenCache() throws Exception {
        // 这里true也表示缓存节点数据
        PathChildrenCache pathChildrenCache = new PathChildrenCache(CLIENT, "/curator", true);

        // 指定初始化的模式
        // NORMAL：普通异步初始化
        // BUILD_INITIAL_CACHE：同步初始化
        // POST_INITIALIZED_EVENT：异步初始化，初始化后会触发事件
        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        // 获取子节点的视图列表，当节点不存在或子节点不存时，List为empty
        // 注意：当初始化模式为异步时，这里List为empty
        List<ChildData> childDataList = pathChildrenCache.getCurrentData();
        System.out.println("获取子节点列表：");
        childDataList.forEach(childData -> {
            log.info("当前子节点：{}，数据{}", childData.getPath(), new String(childData.getData()));
        });

        // 添加Watcher
        pathChildrenCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                // 当初始化模式为POST_INITIALIZED_EVENT时，触发此事件
                case INITIALIZED:
                    System.out.println("PathChildrenCache:子节点初始化成功...");
                    break;
                case CHILD_ADDED:
                    String path = event.getData().getPath();
                    System.out.println("PathChildrenCache添加子节点:" + event.getData().getPath());
                    System.out.println("PathChildrenCache子节点数据:" + new String(event.getData().getData()));
                    break;
                case CHILD_UPDATED:
                    System.out.println("PathChildrenCache修改子节点路径:" + event.getData().getPath());
                    System.out.println("PathChildrenCache修改子节点数据:" + new String(event.getData().getData()));
                    break;
                case CHILD_REMOVED:
                    System.out.println("PathChildrenCache删除子节点:" + event.getData().getPath());
                    break;
                default:

            }
        });

        System.in.read();
    }

    /**
     * Description: 对指定节点及其子节点进行监听，也就是上面两个结合
     */
    @Test
    public void treeCache() throws Exception {
        // 这里true也表示缓存节点数据
        TreeCache treeCache = TreeCache.newBuilder(CLIENT, "/curator").setCacheData(true).build();

        // 注意：添加Watcher的步骤放在start()前后无所谓
        treeCache.getListenable().addListener((client, event) -> {
            if (event.getData() != null) {
                System.out.println("TreeCache,type=" + event.getType() + " path=" + event.getData().getPath());
            } else {
                System.out.println("TreeCache,type=" + event.getType());
            }
        });

        treeCache.start();

        System.in.read();
    }
}
