package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: ConsumerInterceptor 消费者拦截器
 */
@Slf4j
public class Demo10_Interceptor {

    @Test
    public void test() {
        KafkaUtil.DEFAULT_CONSUMER_PROPERTIES.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,
                MyConsumerInterceptor.class.getName());
    }

    /**
     * Description: 自定义消费者拦截器，只消费 10 秒内的消息，过期消息被丢弃
     */
    public static class MyConsumerInterceptor implements ConsumerInterceptor<String, String> {

        private static final long EXPIRE_INTERVAL = 10 * 1000;

        /**
         * Description: 获取消息后触发
         */
        @Override
        public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
            long now = Instant.now().toEpochMilli();
            Map<TopicPartition, List<ConsumerRecord<String, String>>> newRecords = new HashMap<>();
            for (TopicPartition topicPartition : records.partitions()) {
                List<ConsumerRecord<String, String>> tpRecords = records.records(topicPartition);
                List<ConsumerRecord<String, String>> newTpRecords = new ArrayList<>();
                for (ConsumerRecord<String, String> record : tpRecords) {
                    // 只有规定时间内的消息才有效
                    if (now - record.timestamp() < EXPIRE_INTERVAL) {
                        newTpRecords.add(record);
                    }
                }
                if (!newTpRecords.isEmpty()) {
                    newRecords.put(topicPartition, newTpRecords);
                }
            }
            return new ConsumerRecords<>(newRecords);
        }

        /**
         * Description: 提交偏移量后触发
         */
        @Override
        public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
            offsets.forEach((topicPartition, offset) -> {
                log.info("偏移量信息: 分区 {}, 偏移量{}", topicPartition.partition(), offset.offset());
            });
        }

        /**
         * Description: 在关闭时拦截器做一些资源清理的工作
         */
        @Override
        public void close() {
        }

        /**
         * Description: 用于获取配置信息及初始化数据
         */
        @Override
        public void configure(Map<String, ?> configs) {
        }
    }
}
