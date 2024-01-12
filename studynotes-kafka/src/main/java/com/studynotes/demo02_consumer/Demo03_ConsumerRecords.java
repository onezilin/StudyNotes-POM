package com.studynotes.demo02_consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: Consumer 的 ConsumerRecords() 类用于封装一批消息
 */
@Slf4j
public class Demo03_ConsumerRecords {

    @Test
    public void test() {
        DEFAULT_CONSUMER.subscribe(Collections.singletonList(DEFAULT_TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> records = DEFAULT_CONSUMER.poll(Duration.ofSeconds(1));

            // 获取指定主题的消息
            Iterable<ConsumerRecord<String, String>> topicRecordList = records.records(DEFAULT_TOPIC_NAME);

            // 获取指定分区的消息
            List<ConsumerRecord<String, String>> partitionRecordList = records.records(new TopicPartition(DEFAULT_TOPIC_NAME, 0));

            // 获取消息的所有分区信息
            Set<TopicPartition> partitionSet = records.partitions();

            // 获取消息的数量
            int count = records.count();
        }
    }
}
