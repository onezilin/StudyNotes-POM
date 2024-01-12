package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;


/**
 * Description: Consumer 者的 assign() 方法可以订阅指定主题的指定分区
 */
@Slf4j
public class Demo01_Assign {

    @Test
    public void test() {
        // 指定订阅的主题分区， TopicPartition 用于设置主题和分区
        List<TopicPartition> list = Collections.singletonList(new TopicPartition(DEFAULT_TOPIC_NAME, 0));
        DEFAULT_CONSUMER.assign(list);
        while (true) {
            ConsumerRecords<String, String> messages = DEFAULT_CONSUMER.poll(Duration.ofMillis(1000));
            KafkaUtil.printMessage(messages);
        }
    }
}
