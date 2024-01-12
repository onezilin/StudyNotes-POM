package com.studynotes.demo04_transaction;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static com.studynotes.KafkaUtil.*;

/**
 * Description: 测试事务
 */
@Slf4j
public class Demo01_Transaction {

    @Test
    public void producerTransaction() {
        // 设置事务Id，同时默认会开启幂等
        DEFAULT_PRODUCER_PROPERTIES.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactionalId");
        KafkaProducer<String, String> producer = new KafkaProducer<>(DEFAULT_PRODUCER_PROPERTIES);
        producer.initTransactions();
        producer.beginTransaction();

        try {
            IntStream.range(0, 1000).forEach(i -> {
                ProducerRecord<String, String> message = new ProducerRecord<>(DEFAULT_TOPIC_NAME, 0,
                        "" + i, "hello, java, Kafka! 2021/6/14-" + i);
                try {
                    RecordMetadata recordMetadata = producer.send(message).get();
                    log.info("topic名: {}, partition名: {}, offset: {}", recordMetadata.topic(),
                            recordMetadata.partition(),
                            recordMetadata.offset());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            // commitTransaction() 方法提交后，开启 read_committed 隔离级别的消费者可以消费
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            // abortTransaction() 方法遗弃后，开启 read_committed 隔离级别的消费者会跳过这些数据
            producer.abortTransaction();
            e.printStackTrace();
        }
    }

    @Test
    public void consumerTransaction() {
        // 设置消费者隔离级别为 read_committed
        DEFAULT_CONSUMER_PROPERTIES.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(DEFAULT_CONSUMER_PROPERTIES);
        consumer.subscribe(Collections.singletonList(DEFAULT_TOPIC_NAME));
        while (true) {
            ConsumerRecords<String, String> messages = consumer.poll(Duration.ofMillis(1000));
            KafkaUtil.printMessage(messages);
        }
    }
}
