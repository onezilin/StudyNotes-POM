package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: Consumer 提供 seek() 方法从指定分区的指定偏移量，开始消费
 */
@Slf4j
public class Demo07_Seek {

    public static void main(String[] args) {
        DEFAULT_CONSUMER.subscribe(Collections.singleton(DEFAULT_TOPIC_NAME));
        Set<TopicPartition> assignment = new HashSet<>();

        // 进行分区分配：如果不为0, 则说明已经成功分配到了分区
        while (assignment.isEmpty()) {
            DEFAULT_CONSUMER.poll(Duration.ofMillis(100));
            assignment = DEFAULT_CONSUMER.assignment();
        }

        // 为分区指定 offset
        for (TopicPartition tp : assignment) {
            DEFAULT_CONSUMER.seek(tp, 10);
        }

        while (true) {
            ConsumerRecords<String, String> records = DEFAULT_CONSUMER.poll(Duration.ofMillis(1000));
            KafkaUtil.printMessage(records);
        }
    }
}
