package com.studynotes.demo02_redisTemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Description: 测试 Hyperloglog 类型的操作
 */
@SpringBootTest
@Slf4j
public class Demo06_HyperloglogTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // hyperloglog类型是基数，存储的是元素的hash值的集合，返回的是集合不重复的值的个数
    @Test
    public void hyperloglogTest() {
        // pfadd h4 a b c d a
        redisTemplate.opsForHyperLogLog().add("h4", "a", "b", "c", "d", "a");
        redisTemplate.opsForHyperLogLog().add("h5", "a", "z");
        // pfcount h4
        System.out.println(redisTemplate.opsForHyperLogLog().size("h4"));
        // pfmerge des_h6 h4 h5
        redisTemplate.opsForHyperLogLog().union("des_h6", "h4", "h5");
        System.out.println(redisTemplate.opsForHyperLogLog().size("des_h6"));
    }
}
