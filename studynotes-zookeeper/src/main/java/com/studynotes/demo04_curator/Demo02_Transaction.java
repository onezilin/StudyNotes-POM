package com.studynotes.demo04_curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Description: ZooKeeper 支持事务，Curator 提供具体事务操作的 API
 */
public class Demo02_Transaction {

    private static final CuratorFramework CLIENT = CuratorUtil.CLIENT;

    private static final String PATH = CuratorUtil.PATH;

    @Test
    public void transaction() throws Exception {
        // 定义几个基本操作
        CuratorOp createOp = CLIENT.transactionOp().create()
                .forPath(PATH + "/one_path", "some data".getBytes());

        CuratorOp setDataOp = CLIENT.transactionOp().setData()
                .forPath(PATH, "other data".getBytes());

        // 【/curator】节点下有子节点，会删除失败
        CuratorOp deleteOp = CLIENT.transactionOp().delete()
                .forPath(PATH);

        // 事务执行结果
        // 删除【/curator】节点失败，导致事务回滚，其他的操作也失败
        List<CuratorTransactionResult> results = CLIENT.transaction()
                .forOperations(createOp, setDataOp, deleteOp);

        // 遍历输出结果
        for (CuratorTransactionResult result : results) {
            System.out.println("执行结果是： " + result.getForPath() + "--" + result.getType());
        }
    }
}
