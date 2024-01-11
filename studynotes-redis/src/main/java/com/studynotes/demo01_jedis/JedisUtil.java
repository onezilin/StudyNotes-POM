package com.studynotes.demo01_jedis;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Description:
 */
public class JedisUtil {

    // 可用连接实例的最大数目，默认为8
    private static final Integer MAX_TOTAL = 1024;
    // 控制一个 pool 最多有多少个状态为 idle(空闲)的jedis实例，默认值是8
    private static final Integer MAX_IDLE = 200;
    // 等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static final Integer MAX_WAIT_MILLIS = 10000;
    // 在用一个jedis实例时，是否提前进行validate(验证)操作：如果为true，则得到的jedis实例均是可用的
    private static final Boolean TEST_ON_BORROW = true;
    // 在空闲时检查有效性, 默认false
    private static final Boolean TEST_WHILE_IDLE = true;
    // 是否进行有效性检查
    private static final Boolean TEST_ON_RETURN = true;
    // 访问密码
    public static final String AUTH = "";

    public static final JedisPoolConfig CONFIG = new JedisPoolConfig();

    static {
        CONFIG.setMaxTotal(MAX_TOTAL);
        CONFIG.setMaxIdle(MAX_IDLE);
        CONFIG.setMaxWaitMillis(MAX_WAIT_MILLIS);
        CONFIG.setTestOnBorrow(TEST_ON_BORROW);
        CONFIG.setTestWhileIdle(TEST_WHILE_IDLE);
        CONFIG.setTestOnReturn(TEST_ON_RETURN);
    }
}
