package com.studynotes.demo01_producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static com.studynotes.KafkaUtil.DEFAULT_PRODUCER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: Kafka 生产者生产消息
 */
public class Demo01_Produce {

    @Test
    public void produce() {
        IntStream.range(0, 1000).forEach(i -> {
            // 指定消息 topic 和 分区
            ProducerRecord<String, String> message = new ProducerRecord<>(DEFAULT_TOPIC_NAME, 0,
                    "" + i, "hello, java, Kafka! 2021/6/3-" + i);
            try {
                DEFAULT_PRODUCER.send(message).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        DEFAULT_PRODUCER.close();
    }
}
