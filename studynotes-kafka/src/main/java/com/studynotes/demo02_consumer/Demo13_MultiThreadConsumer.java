package com.studynotes.demo02_consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER_PROPERTIES;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: KafkaConsumer 是线程不安全的，多线程处理records时，手动提交偏移量
 */
public class Demo13_MultiThreadConsumer {

    private static final Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

    public static void main(String[] args) {
        new KafkaConsumerThread(Runtime.getRuntime().availableProcessors()).start();
    }

    public static class KafkaConsumerThread extends Thread {

        private final KafkaConsumer<String, String> CONSUMER;

        private final ExecutorService EXECUTOR;

        KafkaConsumerThread(int threadNum) {
            CONSUMER = new KafkaConsumer<>(DEFAULT_CONSUMER_PROPERTIES);
            CONSUMER.subscribe(Collections.singletonList(DEFAULT_TOPIC_NAME));
            EXECUTOR = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(100));
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = CONSUMER.poll(Duration.ofSeconds(1));
                    EXECUTOR.execute(new RecordsHandlerWithCommit(records));

                    // 提交偏移量
                    synchronized (offsets) {
                        if (!offsets.isEmpty()) {
                            CONSUMER.commitSync(offsets);
                            offsets.clear();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                CONSUMER.close();
            }
        }
    }

    public static class RecordsHandlerWithCommit implements Runnable {

        private final ConsumerRecords<String, String> RECORDS;

        RecordsHandlerWithCommit(ConsumerRecords<String, String> records) {
            this.RECORDS = records;
        }

        @Override
        public void run() {
            for (TopicPartition topicPartition : RECORDS.partitions()) {
                List<ConsumerRecord<String, String>> tpRecords = RECORDS.records(topicPartition);
                long lastConsumedOffset = tpRecords.get(tpRecords.size() - 1).offset();

                // 加锁，记录每个分区的最大偏移量
                synchronized (offsets) {
                    if (!offsets.containsKey(topicPartition)) {
                        offsets.put(topicPartition, new OffsetAndMetadata(lastConsumedOffset + 1));
                    } else {
                        long position = offsets.get(topicPartition).offset();
                        if (position < lastConsumedOffset + 1) {
                            offsets.put(topicPartition, new OffsetAndMetadata(lastConsumedOffset + 1));
                        }
                    }
                }
            }
        }
    }
}
