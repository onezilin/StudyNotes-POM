package com.studynotes.demo01_producer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: Kafka 消费者消费消息
 */
@Slf4j
public class Demo02_Consumer {

    @Test
    public void consume() {
        // 消费者订阅 topic
        DEFAULT_CONSUMER.subscribe(Collections.singletonList(DEFAULT_TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> messages = DEFAULT_CONSUMER.poll(0);
            KafkaUtil.printMessage(messages);
        }
    }
}
