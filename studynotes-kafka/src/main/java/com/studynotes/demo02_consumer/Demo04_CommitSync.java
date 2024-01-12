package com.studynotes.demo02_consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER_PROPERTIES;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: 关闭 Consumer 自动提交，使用 commitSync() 方法同步手动提交偏移量
 */
public class Demo04_CommitSync {

    /**
     * Description: 自动提交偏移量
     */
    @Test
    public void commitSyncTest1() {
        KafkaConsumer<String, String> consumer = getConsumer();
        int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            // 自定义的提交时机
            if (buffer.size() > minBatchSize) {
                consumer.commitSync();
                buffer.clear();
            }
        }
    }

    /**
     * Description: 提交指定分区的在指定偏移量
     */
    @Test
    public void commitSyncTest2() {
        KafkaConsumer<String, String> consumer = getConsumer();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                // 当前读取到的消息的偏移量
                long offset = record.offset();
                TopicPartition topicPartition = new TopicPartition(record.topic(), record.partition());
                System.out.println(offset);
                // 注意：提交的偏移量的当前 offset + 1
                consumer.commitSync(Collections.singletonMap(topicPartition, new OffsetAndMetadata(offset + 1)));
            }
        }
    }

    public static KafkaConsumer<String, String> getConsumer() {
        // 关闭自动提交
        DEFAULT_CONSUMER_PROPERTIES.put("enable.auto.commit", false);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(DEFAULT_CONSUMER_PROPERTIES);
        consumer.subscribe(Collections.singleton(DEFAULT_TOPIC_NAME));
        return consumer;
    }
}
