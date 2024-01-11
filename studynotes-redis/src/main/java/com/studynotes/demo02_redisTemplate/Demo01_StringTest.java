package com.studynotes.demo02_redisTemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Description: 测试 String 类型的操作
 */
@Slf4j
@SpringBootTest
public class Demo01_StringTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "stringKey";

    private static final String VALUE = "stringValue";

    @AfterEach
    public void get() {
        // get key
        String value = (String) redisTemplate.opsForValue().get(KEY);
        log.info("key: {}, value: {}", KEY, value);
    }

    @Test
    public void set() {
        // set key value
        redisTemplate.opsForValue().set(KEY, VALUE);
    }

    @Test
    public void setnx() {
        // setnx key value
        Boolean result = redisTemplate.opsForValue().setIfAbsent(KEY, "value");
        log.info("result: {}", result);
    }

    @Test
    public void del() {
        // del key
        redisTemplate.delete(KEY);
    }

    @Test
    public void strlen() {
        // strlen key
        Long length = redisTemplate.opsForValue().size(KEY);
        System.out.println("length: " + length);
    }

    @Test
    public void getset() {
        // getset key new_value
        String oldValue = (String) redisTemplate.opsForValue().getAndSet(KEY, "new_value");
        log.info("oldValue: {}", oldValue);
    }

    @Test
    public void getrange() {
        // getrange key start end
        String rangeValue = redisTemplate.opsForValue().get(KEY, 0, 3);
        log.info("0~3 rangeValue: {}", rangeValue);
    }

    @Test
    public void append() {
        // append key value
        int newLen = redisTemplate.opsForValue().append(KEY, "_app");
        log.info("newLen: {}", newLen);
    }

    // 对数据进行加减操作
    // 注意不同的序列化器对值的序列化操作是不同的，下面值序列化器需要使用 StringRedisSerializer
    @Test
    public void incr() {
        // set key value
        redisTemplate.opsForValue().set(KEY, 1.1);
        // incrbyfloat key
        redisTemplate.opsForValue().increment(KEY, 3);
        // decr key value
        byte[] key = ((StringRedisSerializer) redisTemplate.getKeySerializer()).serialize(KEY);
        assert key != null;
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().decrBy(key, 1);
    }
}
