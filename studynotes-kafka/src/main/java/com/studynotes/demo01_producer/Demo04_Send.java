package com.studynotes.demo01_producer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;

import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: Kafka 生产者发送消息的三种方式
 */
@Slf4j
public class Demo04_Send {

    // 异步发送
    @Test
    public void asyncSend() {
        String value = "hello, java, Kafka, asyncSend!";
        ProducerRecord<String, String> message = new ProducerRecord<>(DEFAULT_TOPIC_NAME, value);
        KafkaUtil.DEFAULT_PRODUCER.send(message);
    }

    // 同步发送
    @Test
    public void syncSend() {
        String value = "hello, java, Kafka, syncSend!";
        ProducerRecord<String, String> message = new ProducerRecord<>(DEFAULT_TOPIC_NAME, value);
        try {
            RecordMetadata recordMetadata = KafkaUtil.DEFAULT_PRODUCER.send(message).get();
            log.info("topic名: {}, partition名: {}, offset: {}", recordMetadata.topic(), recordMetadata.partition(),
                    recordMetadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 带有回调函数的发送
    @Test
    public void callbackSend() {
        String value = "hello, java, Kafka, callbackSend!";
        ProducerRecord<String, String> message = new ProducerRecord<>(DEFAULT_TOPIC_NAME, value);
        // 发送后执行回调函数
        KafkaUtil.DEFAULT_PRODUCER.send(message, (recordMetadata, exception) -> {
            if (exception != null) {
                log.info("发送消息出错: {}", exception.getMessage());
            } else {
                log.info("topic名: {}, partition名: {}, offset: {}", recordMetadata.topic(), recordMetadata.partition(),
                        recordMetadata.offset());
            }
        });
        // close会阻塞等待之前所有的发送请求完成
        KafkaUtil.DEFAULT_PRODUCER.close();
    }
}
