package com.studynotes.demo04_curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Description: <a href="https://kaiwu.lagou.com/course/courseInfo.htm?courseId=393#/detail/pc?id=4467">Curator 基本使用</a>
 */
@Slf4j
public class Demo01_CuratorApi {

    private static final CuratorFramework CLIENT = CuratorUtil.CLIENT;

    private static final String PATH = CuratorUtil.PATH;

    /**
     * Description: 创建节点
     */
    @Test
    public void createTest() throws Exception {
        // 创建ZNode节点、节点类型、节点路径、节点数据，返回节点路径
        // 注意：创建节点时，其父节点必须存在，例如：直接创建【/curator/test】则报错
        String znodePath =
                CLIENT.create().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(PATH,
                        "myData".getBytes());
        log.info("ZNode路径：{}", znodePath);
    }

    /**
     * Description: 检查节点是否存在
     */
    @Test
    public void check() throws Exception {
        Stat stat = CLIENT.checkExists().forPath(PATH);
        log.info("stat: {}", stat);
    }

    /**
     * Description: 设置、获取ZNode中的数据
     */
    @Test
    public void getData() throws Exception {
        Stat stat = CLIENT.setData().forPath(PATH, "new Data".getBytes());

        byte[] data = CLIENT.getData().forPath(PATH);
        System.out.println(new String(data));
    }

    /**
     * Description: 创建、获取和删除ZNode节点
     */
    @Test
    public void createDelete() throws Exception {
        for (int i = 0; i < 3; i++) {
            // 创建多个临时顺序节点
            CLIENT.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(PATH + "/child-");
        }

        // 获取所有节点，添加数据
        List<String> childrenPathList = CLIENT.getChildren().forPath(PATH);
        System.out.println(childrenPathList);
        childrenPathList.forEach(childPath -> {
            try {
                CLIENT.setData().forPath(PATH + "/" + childPath, ("new Data" + childPath).getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 删除本节点及子节点
        CLIENT.delete().deletingChildrenIfNeeded().forPath(PATH);
    }
}
