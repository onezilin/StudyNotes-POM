package com.studynotes.demo02_consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER_PROPERTIES;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: lastConsumedOffset、committedOffset  和 position 的区别
 * <p>
 * 1. lastConsumedOffset：当前消费的 offset
 * 2. committedOffset：最近一次提交的 offset
 * 3. position：下一次拉取消息的 offset
 */
public class Demo14_CommittedOffset {

    @Test
    public void test() {
        TopicPartition topicPartition = new TopicPartition(DEFAULT_TOPIC_NAME, 0);
        // 关闭自动提交
        DEFAULT_CONSUMER_PROPERTIES.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 设置每次拉取的最大消息数
        DEFAULT_CONSUMER_PROPERTIES.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(DEFAULT_CONSUMER_PROPERTIES);

        consumer.assign(Collections.singletonList(topicPartition));
        ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(2));

        if (!messages.isEmpty()) {
            List<ConsumerRecord<String, String>> list = messages.records(topicPartition);
            long lastConsumedOffset = list.get(list.size() - 1).offset();
            System.out.println("lastConsumedOffset：" + lastConsumedOffset); // 9
            long committedOffset = consumer.committed(topicPartition).offset();
            System.out.println("提交前 committedOffset：" + committedOffset); // 0
            long position = consumer.position(topicPartition);
            System.out.println("提交前 position：" + position); // 10
        }

        consumer.commitSync();

        long committedOffset = consumer.committed(topicPartition).offset();
        System.out.println("提交后 committedOffset：" + committedOffset); // 10
        long position = consumer.position(topicPartition);
        System.out.println("提交后 position：" + position); // 10
    }
}
