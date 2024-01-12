package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: Consumer 中 offsetsForTimes() 方法，通过指定时间戳获取符合条件（大于等于时间戳的第一条消息对应的位置和时间戳）的offset，
 * 实现从指定时间戳开始消费的功能
 */
@Slf4j
public class Demo08_OffsetsForTimes {

    @Test
    public void test() {
        DEFAULT_CONSUMER.subscribe(Collections.singleton(DEFAULT_TOPIC_NAME));
        // 先进行一次 poll，获取分区信息
        DEFAULT_CONSUMER.poll(Duration.ofSeconds(5));
        Set<TopicPartition> topicPartitionList = DEFAULT_CONSUMER.assignment();

        // 获取一天前的时间戳，并设置 topicPartitionList 中每个分区的时间戳消费的最小时间戳
        long timestamp = LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Map<TopicPartition, Long> timestampToSearch = new HashMap<>();
        topicPartitionList.forEach(topicPartition -> timestampToSearch.put(topicPartition, timestamp));

        // 通过 timestampToSearch 查询对应分区中满足条件的 offset
        Map<TopicPartition, OffsetAndTimestamp> offsets = DEFAULT_CONSUMER.offsetsForTimes(timestampToSearch);
        topicPartitionList.forEach(topicPartition -> {
            OffsetAndTimestamp offsetAndTimestamp = offsets.get(topicPartition);
            if (offsetAndTimestamp != null) {
                long offset = offsetAndTimestamp.offset();
                log.info("一天前partition: {}, 的最近消息的位置: {}", topicPartition.partition(), offset);
                DEFAULT_CONSUMER.seek(topicPartition, offset);
            }
        });

        while (true) {
            ConsumerRecords<String, String> records = DEFAULT_CONSUMER.poll(Duration.ofMillis(1000));
            KafkaUtil.printMessage(records);
        }
    }
}
