package com.studynotes.demo02_redisTemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import javax.annotation.Resource;

/**
 * Description: 测试事务
 */
@Slf4j
@SpringBootTest
public class Demo07_Transaction {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void transactionTest() {
        // session可以保证resdis连接是同一个
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                ops.multi();
                ops.boundValueOps("key2").set("value2");
                String value = (String) ops.boundValueOps("key2").get();
                System.out.println("看看加了事务的后会不会立刻返回值" + value);
                ops.exec();
                value = (String) redisTemplate.opsForValue().get("key2");
                // 这里返回什么下面的值就是什么
                return value;
            }
        };
        String value = (String) redisTemplate.execute(sessionCallback);
        System.out.println(value);
    }
}
