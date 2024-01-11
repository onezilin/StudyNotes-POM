package com.studynotes.demo01_jedis;

import com.studynotes.constant.CommonConstants;
import lombok.Getter;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Description: 单个 Redis 连接
 */
public class Demo01_JedisClient {

    @Getter
    private static Jedis jedis;

    static {
        // 使用配置创建连接池
        try (JedisPool pool = new JedisPool(JedisUtil.CONFIG, CommonConstants.LINUX_HOST, CommonConstants.REDIS_PORT)) {
            // 从连接池中获取单个连接
            jedis = pool.getResource();
            if (StringUtils.hasText(JedisUtil.AUTH)) {
                jedis.auth(JedisUtil.AUTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
