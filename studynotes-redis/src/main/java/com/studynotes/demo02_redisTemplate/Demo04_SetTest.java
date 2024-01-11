package com.studynotes.demo02_redisTemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Description: 测试 Set 类型的操作
 */
@Slf4j
@SpringBootTest
public class Demo04_SetTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void setTest() {
        BoundSetOperations<String, Object> skey3 = redisTemplate.boundSetOps("skey3");
        BoundSetOperations<String, Object> skey4 = redisTemplate.boundSetOps("skey4");
        skey3.add("vl", "v2", "v3", "v4", "v5", "v6");
        skey4.add("v0", "v2", "v4", "v6", "v8");
        // scard skey3
        System.out.println(skey3.size());
        // sdiff skey3 skey4
        System.out.println(skey3.diff("skey4"));
        // sinter skey3 skey4
        System.out.println(skey3.intersect("skey4"));
        // sunion skey3 skey4
        System.out.println(skey3.union("skey4"));
        // sismember skey3 v1
        System.out.println(skey3.isMember("v1"));
        // smembers skey3
        System.out.println(skey3.members());
        // spop skey3
        System.out.println(skey3.pop());
        // srandmember skey3 2
        skey3.randomMembers(2L);
        // sremove skey3 v1 v2
        skey3.remove("v1", "v2");
        // sunionstore skey3 skey4 union_skey2
        skey3.unionAndStore("skey4", "union_skey2");
        System.out.println(redisTemplate.opsForSet().members("union_skey2"));
        // sinterstore skey3 skey4 inter_skey2
        skey3.intersectAndStore("skey4", "inter_skey2");
        System.out.println(redisTemplate.opsForSet().members("inter_skey2"));
        // sdiffstore skey3 skey4 diff_skey2
        skey3.diffAndStore("skey4", "diff_skey2");
        System.out.println(redisTemplate.opsForSet().members("diff_skey2"));
    }
}
