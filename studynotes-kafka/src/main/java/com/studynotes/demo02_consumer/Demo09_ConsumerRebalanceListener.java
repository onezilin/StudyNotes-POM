package com.studynotes.demo02_consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: 实现 ConsumerRebalanceListener 再均衡监听器接口，可以在再均衡发生时，进行一些操作
 */
public class Demo09_ConsumerRebalanceListener {

    private static final AtomicBoolean IS_RUNNING = new AtomicBoolean(true);

    /**
     * Description: 平时是 commitAsync() 异步提交，但是发生再均衡时，使用 commitSync() 同步提交
     */
    @Test
    public void test() {
        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
        // 创建再均衡监听器
        ConsumerRebalanceListener rebalanceListener = new MyConsumerRebalanceListener(DEFAULT_CONSUMER, currentOffsets);
        DEFAULT_CONSUMER.subscribe(Collections.singletonList(DEFAULT_TOPIC_NAME), rebalanceListener);

        try {
            while (IS_RUNNING.get()) {
                ConsumerRecords<String, String> records = DEFAULT_CONSUMER.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    currentOffsets.put(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1));
                    DEFAULT_CONSUMER.commitAsync(currentOffsets, null);
                }
            }
        } finally {
            DEFAULT_CONSUMER.close();
        }
    }

    /**
     * Description: 再均衡监听器，发生再均衡时，使用 commitSync() 同步提交
     */
    static class MyConsumerRebalanceListener implements ConsumerRebalanceListener {

        // 消费者
        private final KafkaConsumer<String, String> CONSUMER;
        // 用于记录当前消费者偏移量
        private final Map<TopicPartition, OffsetAndMetadata> CURRENT_OFFSETS;

        public MyConsumerRebalanceListener(KafkaConsumer<String, String> consumer, Map<TopicPartition,
                OffsetAndMetadata> currentOffsets) {
            this.CONSUMER = consumer;
            this.CURRENT_OFFSETS = currentOffsets;
        }

        /**
         * Description: 在再均衡开始之前和消费者停止读取消息之后被调用
         */
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            CONSUMER.commitSync(CURRENT_OFFSETS);
            CURRENT_OFFSETS.clear();
        }

        /**
         * Description: 在重新分配分区之后和消费者开始读取消费之前被调用
         */
        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        }

        @Override
        public void onPartitionsLost(Collection<TopicPartition> partitions) {
            ConsumerRebalanceListener.super.onPartitionsLost(partitions);
        }
    }
}
