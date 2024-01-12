package com.studynotes.demo05_streams;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.time.Duration;
import java.util.*;

import static com.studynotes.KafkaUtil.*;


/**
 * Description: 实现 consumer-transfer-produce 模式，即从 topic-source 消费消息，传入 topic-sink
 */
public class Demo01_SourceToSink {

    private static final String TOPIC_SOURCE = "topic-source";

    private static final String TOPIC_SINK = "topic-sink";

    public static Properties getConsumerProperties() {
        Properties props = DEFAULT_CONSUMER_PROPERTIES;
        // 关闭自动提交，并且也不能手动提交消费位移
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return props;
    }

    public static Properties getProducerProperties() {
        Properties props = DEFAULT_PRODUCER_PROPERTIES;
        // 设置事务Id，同时默认会开启幂等
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactionalId");
        return props;
    }

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties());
        consumer.subscribe(Collections.singleton(TOPIC_SOURCE));
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProducerProperties());
        // 初始化事务
        producer.initTransactions();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            if (!records.isEmpty()) {
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                // 开启事务
                try {
                    producer.beginTransaction();
                    for (TopicPartition partition : records.partitions()) {
                        List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                        for (ConsumerRecord<String, String> record : partitionRecords) {
                            // TODO: do some logical processing
                            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_SINK,
                                    record.key()
                                    , record.value());
                            // 消费-生产模型
                            producer.send(producerRecord);
                        }
                        long lastCosumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                        offsets.put(partition, new OffsetAndMetadata(lastCosumedOffset + 1));
                    }
                    // 生产者事务提交位移
                    producer.sendOffsetsToTransaction(offsets, DEFAULT_CONSUMER_GROUP);
                    // 提交事务
                    producer.commitTransaction();
                } catch (ProducerFencedException e) {
                    // 中止事务
                    producer.abortTransaction();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
