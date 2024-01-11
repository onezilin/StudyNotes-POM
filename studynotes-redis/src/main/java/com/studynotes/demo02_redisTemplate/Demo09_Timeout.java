package com.studynotes.demo02_redisTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description: 测试超时
 */
@SpringBootTest
public class Demo09_Timeout {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void timeTest() {
        BoundValueOperations key1 = redisTemplate.boundValueOps("key1");
        key1.set("value1");
        System.out.println(key1.get());
        // ttl key1
        System.out.println(key1.getExpire());
        // expire key1 120
        key1.expire(120, TimeUnit.SECONDS);
        System.out.println(key1.getExpire());
        // persist key1
        key1.persist();
        System.out.println(key1.getExpire());
        // expireat key1 (now+120000)这个只是比做成现在时间加120秒
        long now = System.currentTimeMillis();
        Date date = new Date();
        date.setTime(now + 120000);
        key1.expireAt(date);
        System.out.println(key1.getExpire());
    }
}
