package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: Consumer 提供 pause() 和 resume() 方法暂停和恢复消费指定分区
 * <p>
 * 注意：暂停消费是指暂停 poll() 方法的调用，即不再消费新的消息，但是不会影响到已经消费的消息
 */
@Slf4j
public class Demo06_PauseAndResume {

    @Test
    public void test() {
        KafkaConsumer<String, String> consumer = getConsumer();
        int count = 0;
        Set<TopicPartition> pausePartition = new HashSet<>();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            KafkaUtil.printMessage(records);

            count++;

            if (count == 5) {
                pausePartition = records.partitions();
                consumer.pause(pausePartition);
                log.info("暂停消费分区: {}", pausePartition);
            } else if (count > 5 && count < 10) {
                log.info("进行其他业务");
            } else if (count == 10) {
                log.info("获取已暂停消费分区: {}", consumer.paused());
                consumer.resume(pausePartition);
            }
        }
    }

    public static KafkaConsumer<String, String> getConsumer() {
        // 一次轮询最多获取 10 条消息
        KafkaUtil.DEFAULT_CONSUMER_PROPERTIES.put("max.poll.records", 10);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaUtil.DEFAULT_CONSUMER_PROPERTIES);
        consumer.subscribe(Collections.singleton(KafkaUtil.DEFAULT_TOPIC_NAME));
        return consumer;
    }
}
