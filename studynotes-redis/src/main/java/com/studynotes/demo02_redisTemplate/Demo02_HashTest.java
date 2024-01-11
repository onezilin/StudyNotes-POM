package com.studynotes.demo02_redisTemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 测试 Hash 类型的操作
 */
@Slf4j
@SpringBootTest
public class Demo02_HashTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "hashKey";

    private static final String FIELD_KEY_1 = "hashField1";

    private static final String FIELD_VALUE_1 = "hashValue1";

    private static final String FIELD_KEY_2 = "hashField2";

    private static final String FIELD_VALUE_2 = "6";

    Map<String, String> map = new HashMap<>();

    {
        map.put(FIELD_KEY_1, FIELD_VALUE_1);
    }

    @AfterEach
    public void get() {
        // hgetall key
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(KEY);
        log.info("key: {}, value: {}", KEY, entries);
    }

    @Test
    public void hashTest() {
        redisTemplate.opsForHash().putAll(KEY, map);

        // hset key field value
        redisTemplate.opsForHash().put(KEY, FIELD_KEY_2, FIELD_VALUE_2);

        // hexists key field
        redisTemplate.opsForHash().hasKey(KEY, FIELD_KEY_2);

        // hkeys key
        redisTemplate.opsForHash().keys(KEY);

        // hvals key
        redisTemplate.opsForHash().values(KEY);

        // hdel key field1 field2
        redisTemplate.opsForHash().delete(KEY, FIELD_KEY_1);
    }
}
