package com.studynotes.demo02_redisTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description: 测试 List 类型的操作
 */
@Slf4j
@SpringBootTest
public class Demo03_ListTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "listKey";

    @Test
    @SneakyThrows
    public void linkedlistTest() {
        // lpush lkey3 node3
        redisTemplate.opsForList().leftPush(KEY, "node3");

        // lpush lkey3 node2 node1
        List<String> nodeList = new ArrayList<>();
        for (int i = 2; i >= 1; i--) {
            nodeList.add("node" + i);
        }
        redisTemplate.opsForList().leftPushAll(KEY, nodeList);

        // rpush lkey3 node4
        redisTemplate.opsForList().rightPush(KEY, "node4");

        // lindex lkey3 0
        String node1 = (String) redisTemplate.opsForList().index(KEY, 0);
        System.out.println(node1);

        // llen lkey3
        long size = redisTemplate.opsForList().size(KEY);

        // lpop lkey3
        System.out.println(redisTemplate.opsForList().leftPop(KEY).toString());

        // rpoo lkey3
        System.out.println(redisTemplate.opsForList().rightPop(KEY).toString());

        // linsert 需要使用更为底层的命令
        // linsert lkey3 before node2 before_node2
        redisTemplate.getConnectionFactory().getConnection().lInsert(KEY.getBytes("UTF-8")
                , RedisListCommands.Position.BEFORE, "node2".getBytes("UTF-8"), "before_node2".getBytes("UTF-8"));
        // linsert lkey3 after node2 after_node2
        redisTemplate.getConnectionFactory().getConnection().lInsert(KEY.getBytes("UTF-8")
                , RedisListCommands.Position.AFTER, "node2".getBytes("UTF-8"), "after_node2".getBytes("UTF-8"));

        // lpushx lkey3 head
        redisTemplate.opsForList().leftPushIfPresent(KEY, "head");

        // rpushx lkey3 end
        redisTemplate.opsForList().rightPushIfPresent(KEY, "end");

        // lrange lkey3 0 10
        System.out.println(redisTemplate.opsForList().range(KEY, 0, 10));

        // lrem lkey3 2 node
        List<String> nodeList1 = new ArrayList<>();
        for (int i = 3; i >= 1; i--) {
            nodeList1.add("node");
        }
        redisTemplate.opsForList().leftPushAll(KEY, nodeList1);
        redisTemplate.opsForList().remove(KEY, 2, "node");

        // lset lkey3 0 set_2_node
        redisTemplate.opsForList().set(KEY, 2, "set_2_node");

        // ltrim lkey3 0 3
        redisTemplate.opsForList().trim(KEY, 0, 3);

        // rpoplpush lkey2 lkey3
        redisTemplate.opsForList().rightPopAndLeftPush("lkey2", KEY);

        // 对链表堵塞命令,保证数据的一致而性能不佳
        // blpop lkey3 1
        redisTemplate.opsForList().leftPop(KEY, 1, TimeUnit.SECONDS);

        // brpop lkey3 1
        redisTemplate.opsForList().rightPop(KEY, 1, TimeUnit.SECONDS);

        // brpoplpush lkey2 lkey3 1
        redisTemplate.opsForList().rightPopAndLeftPush("lkey2", KEY, 1, TimeUnit.SECONDS);
    }

    // 测试Linked-list类型的阻塞命令
    @Test
    public void bLinkedlistTest() {
        redisTemplate.delete(KEY);
        String s1 = (String) redisTemplate.opsForList().leftPop(KEY, 30, TimeUnit.SECONDS);
        System.out.println("等等等等" + s1);
        redisTemplate.opsForList().leftPush(KEY, "node2");
        System.out.println("继续");
    }
}
