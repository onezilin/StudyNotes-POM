package com.studynotes.demo01_jedis;

import com.studynotes.constant.CommonConstants;
import lombok.Getter;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: Redis 集群
 */
public class Demo02_JedisCluster {

    @Getter
    private static JedisCluster jedis;

    static {
        Set<HostAndPort> jedisClusterNode = Arrays.stream(CommonConstants.REDIS_CLUSTER_PORTS)
                .mapToObj(port -> new HostAndPort(CommonConstants.LINUX_HOST, port))
                .collect(Collectors.toSet());

        jedis = new JedisCluster(jedisClusterNode, JedisUtil.CONFIG);
    }
}
