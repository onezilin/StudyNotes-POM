package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.IntStream;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER_PROPERTIES;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: 测试在多线程下的表现，每个线程都负责处理自己的消息
 */
@Slf4j
public class Demo11_MultiThreadConsumer {

    @Test
    public void test() {
        IntStream.range(0, 3).forEach(i -> {
            new KafkaConsumerThread(i).start();
        });
    }

    /**
     * Description: 为每个线程分配一个 KafkaConsumer 实例，相当于创建多个消费者
     */
     static class KafkaConsumerThread extends Thread {
        private String name;
        private KafkaConsumer<String, String> consumer;

        KafkaConsumerThread(int i) {
            // 采用 StickyAssignor 分区分配策略，保证每个消费者分配到的分区变化较小
            DEFAULT_CONSUMER_PROPERTIES.setProperty(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
                    StickyAssignor.class.getName());
            DEFAULT_CONSUMER_PROPERTIES.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
            consumer = new KafkaConsumer<>(DEFAULT_CONSUMER_PROPERTIES);
            // 添加再均衡监听器
            consumer.subscribe(Collections.singletonList(DEFAULT_TOPIC_NAME), new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    System.out.println("分区掉线");
                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    System.out.println("分区分配");
                }
            });
            name = "消费者：" + i;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(2000);
                    KafkaUtil.printMessage(records);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                consumer.close();
            }
        }
    }
}
