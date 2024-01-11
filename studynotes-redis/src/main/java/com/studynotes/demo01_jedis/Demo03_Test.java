package com.studynotes.demo01_jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisCluster;

/**
 * Description: 测试 Redis 或 Redis 集群每秒操作次数
 */
public class Demo03_Test {

    @Test
    public void test() {
        // Jedis jedis = Demo01_JedisClient.getJedis();
        JedisCluster jedis = Demo02_JedisCluster.getJedis();

        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    break;
                }
                i++;
                jedis.set("test" + i, "" + i);
            }
        } finally {
            jedis.close();
        }
        System.out.println("redis每秒操作：" + i + "次");
    }
}
