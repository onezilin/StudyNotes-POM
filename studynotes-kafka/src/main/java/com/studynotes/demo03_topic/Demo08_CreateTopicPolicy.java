package com.studynotes.demo03_topic;

import org.apache.kafka.common.errors.PolicyViolationException;
import org.apache.kafka.server.policy.CreateTopicPolicy;

import java.util.Map;

/**
 * Description: CreateTopicPolicy 用于验证 AdminClient 创建的主题是否符合规定，防止创建了不符合规定的主题
 * <p>
 * 测试步骤：
 * 1、将 PolicyDemo 类放到 kafka_2.13-2.7.0\libs 目录下
 * 2、在 kafka_2.13-2.7.0\config\server.properties 文件中添加 create.topic.policy.class=PolicyDemo 配置
 */
public class Demo08_CreateTopicPolicy {

    static class PolicyDemo implements CreateTopicPolicy {

        /**
         * Description: 创建主题时，用于验证topic的相关参数是否合法
         */
        @Override
        public void validate(RequestMetadata requestMetadata) throws PolicyViolationException {
            if (requestMetadata.numPartitions() != null || requestMetadata.replicationFactor() != null) {
                // 验证分区数是否小于5，副本因子是否小于1
                if (requestMetadata.numPartitions() < 5) {
                    throw new PolicyViolationException("Topic should have at least 5 partitions, received: " +
                            requestMetadata.numPartitions());
                }
                if (requestMetadata.replicationFactor() <= 1) {
                    throw new PolicyViolationException("Topic should have at least 1 replicationFactor, received: " +
                            requestMetadata.replicationFactor());
                }
            }
        }

        /**
         * Description: Kafka 服务器关闭时调用
         */
        @Override
        public void close() throws Exception {
        }

        @Override
        public void configure(Map<String, ?> configs) {
        }
    }
}
