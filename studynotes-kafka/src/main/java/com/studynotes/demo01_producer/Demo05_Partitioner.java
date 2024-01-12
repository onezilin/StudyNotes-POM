package com.studynotes.demo01_producer;

import com.studynotes.KafkaUtil;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.Cluster;
import org.junit.jupiter.api.Test;

/**
 * Description: 实现 Partitioner 接口自定义分区器，用于将消息发送到指定的分区
 */
public class Demo05_Partitioner {

    // 将自定义分区器放在生产者配置中
    @Test
    public void test() {
        KafkaUtil.DEFAULT_PRODUCER_PROPERTIES.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class);
    }

    /**
     * Description: 自定义分区器，用于将消息发送到指定的分区
     */
    static class MyPartitioner implements Partitioner {

        /**
         * Description: 用于计算消息发送到哪个分区
         */
        @Override
        public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
                             Cluster cluster) {
            // 获取分区数
            int partitionCount = cluster.partitionCountForTopic(topic);
            // 获取消息的 key
            String keyStr = (String) key;
            // 获取消息的 value
            String valueStr = (String) value;
            // 根据消息的 key 进行分区
            return Integer.parseInt(keyStr.substring(keyStr.lastIndexOf("-") + 1)) % partitionCount;
        }

        /**
         * Description: 在关闭分区时用来回收一些资源
         */
        @Override
        public void close() {
        }

        /**
         * Description: 用于获取配置信息及初始化数据
         */
        @Override
        public void configure(java.util.Map<java.lang.String, ?> configs) {
        }
    }
}
