package com.studynotes.demo03_messageListener;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Description: 测试 Redis 的发布订阅模式
 */
@SpringBootTest
public class Demo02_Test {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public static final String CHANNEL = "chat";

    /**
     * Description:
     * <p>
     * 测试步骤：
     * 1、启动后，查看 MessageListener 实现类是否能监听到对应信息
     */
    @Test
    public void test() {
        redisTemplate.convertAndSend(CHANNEL, "I am lazy!!");
        redisTemplate.convertAndSend(CHANNEL, "I am lazy2!!");

        System.out.println(System.currentTimeMillis());
    }
}
