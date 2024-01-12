package com.studynotes.demo01_producer;

import com.studynotes.constant.CommonConstants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * Description: ProducerConfig 为 Properties 提供了Kafka 配置的键，防止写错
 */
public class Demo03_ProducerConfig {

    @Test
    public void test() {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.KAFKA_BROKER_LIST);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 设置 producer 的 id，默认为 producer-1
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
    }
}
