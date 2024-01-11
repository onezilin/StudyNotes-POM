package com.studynotes.demo02_redisTemplate;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * Description: 测试原生 jedis 的流水线操作
 */
public class Demo08_Pipeline {

    @Test
    public void pipeLine() {
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        // 最大空闲数
        poolCfg.setMaxIdle(50);
        // 最大连接数
        poolCfg.setMaxTotal(100);
        // 最大等待毫秒数
        poolCfg.setMaxWaitMillis(20000);
        // 使用配置创建连接池
        JedisPool pool = new JedisPool(poolCfg, "192.168.190.128");
        // 从连接池中获取单个连接
        Jedis jedis = pool.getResource();

        long start = System.currentTimeMillis();

        // 开启流水线
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 100; i++) {
            int j = i + 1;
            pipeline.set("pipeline_key" + j, "pipeline_value" + j);
            pipeline.get("pipeline_key" + j);
        }
        // 只执行同步，不返回结果，
        // 类似于io的flush()方法，不执行的话，可能会有少部分pipeline方法没有发送
        // 例如100条命令只执行90条
        pipeline.sync();
        // 返回执行过的命令，
        //        List result = pipeline.syncAndReturnAll();
        //        System.out.println(result);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        try {
            pipeline.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
