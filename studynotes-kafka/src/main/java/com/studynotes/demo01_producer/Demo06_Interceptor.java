package com.studynotes.demo01_producer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 实现 PdoducerInterceptor 接口，实现自定义拦截器，在发送前对消息进行处理
 */
public class Demo06_Interceptor {

    // 将自定义拦截器添加到生产者配置中
    @Test
    public void test() {
        KafkaUtil.DEFAULT_PRODUCER_PROPERTIES.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                MyProducerInterceptor1.class.getName() + "," + MyProducerInterceptor2.class.getName());
    }

    @Slf4j
    static class MyProducerInterceptor1 implements ProducerInterceptor<String, String> {

        private final AtomicInteger SUCCESS = new AtomicInteger();
        private final AtomicInteger FAILURE = new AtomicInteger();

        /**
         * Description: 在发送消息前，对消息进行处理
         */
        @Override
        public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
            // 修改消息的 value 值
            String modifiedValue = "prefix-" + record.value();
            return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(),
                    record.key(), modifiedValue);
        }

        /**
         * Description: 在消息被应答之前（消息发送成功）或消息发送失败时调用
         */
        @Override
        public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
            if (e == null) {
                SUCCESS.getAndIncrement();
            } else {
                FAILURE.getAndIncrement();
            }
        }

        /**
         * Description: 在关闭时拦截器做一些资源清理的工作
         */
        @Override
        public void close() {
            double successRatio = (double) SUCCESS.get() / (FAILURE.get() + SUCCESS.get());
            log.info("消息发送成功率: {}%", successRatio * 100);
        }

        /**
         * Description: 用于获取配置信息及初始化数据
         */
        @Override
        public void configure(Map<String, ?> map) {
        }
    }

    @Slf4j
    static class MyProducerInterceptor2 implements ProducerInterceptor<String, String> {

        @Override
        public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
            String modifiedValue = "prefix2-" + producerRecord.value();
            return new ProducerRecord<>(producerRecord.topic(), producerRecord.partition(), producerRecord.timestamp(),
                    producerRecord.key(), modifiedValue, producerRecord.headers());
        }

        @Override
        public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        }

        @Override
        public void close() {
        }

        @Override
        public void configure(Map<String, ?> map) {
        }
    }
}
