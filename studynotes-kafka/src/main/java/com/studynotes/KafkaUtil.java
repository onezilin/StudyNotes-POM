package com.studynotes;

import com.studynotes.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

/**
 * Description: Kafka 工具类
 */
@Slf4j
public class KafkaUtil {

    public static final String DEFAULT_TOPIC_NAME = "myTopic";

    public static final String DEFINE_TOPIC_NAME = "my-kafka-topic-demo";

    public static final String DEFAULT_CONSUMER_GROUP = "group.demo";

    // Kafka 生产者配置
    public static final Properties DEFAULT_PRODUCER_PROPERTIES;

    static {
        DEFAULT_PRODUCER_PROPERTIES = new Properties();
        DEFAULT_PRODUCER_PROPERTIES.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        DEFAULT_PRODUCER_PROPERTIES.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 生产者连接的 Kafka 集群地址
        DEFAULT_PRODUCER_PROPERTIES.put("bootstrap.servers", CommonConstants.KAFKA_BROKER_LIST);
    }

    // Kafka 生产者
    public static final KafkaProducer<String, String> DEFAULT_PRODUCER;

    static {
        DEFAULT_PRODUCER = new KafkaProducer<>(DEFAULT_PRODUCER_PROPERTIES);
    }

    // Kafka 消费者配置
    public static final Properties DEFAULT_CONSUMER_PROPERTIES;

    static {
        DEFAULT_CONSUMER_PROPERTIES = new Properties();
        DEFAULT_CONSUMER_PROPERTIES.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        DEFAULT_CONSUMER_PROPERTIES.put("value.deserializer", "org.apache.kafka.common.serialization" +
                ".StringDeserializer");
        // 消费者连接的 Kafka 集群地址
        DEFAULT_CONSUMER_PROPERTIES.put("bootstrap.servers", CommonConstants.KAFKA_BROKER_LIST);
        // 消费者所属的消费组
        DEFAULT_CONSUMER_PROPERTIES.put("group.id", DEFAULT_CONSUMER_GROUP);
    }

    // Kafka 消费者
    public static final KafkaConsumer<String, String> DEFAULT_CONSUMER;

    static {
        DEFAULT_CONSUMER = new KafkaConsumer<>(DEFAULT_CONSUMER_PROPERTIES);
    }

    public static void printMessage(ConsumerRecords<String, String> messages) {
        messages.forEach(message -> {
            log.info("topic信息: {topic: {}, partition: {}, offset: {}}", message.topic(), message.partition(),
                    message.offset());
            log.info("消费者获取消息: {key: {}, value: {}}", message.key(), message.value());
        });
    }
}
