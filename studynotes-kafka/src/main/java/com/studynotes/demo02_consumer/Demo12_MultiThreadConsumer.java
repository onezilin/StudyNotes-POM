package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER_PROPERTIES;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: 一个线程分配一个 KafkaConsumer，但是其中消息放在线程池处理
 */
public class Demo12_MultiThreadConsumer {

    @Test
    public void test() {
        new KafkaConsumerThread(Runtime.getRuntime().availableProcessors()).start();
    }

     static class KafkaConsumerThread extends Thread {

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
                    EXECUTOR.execute(new RecordsHandler(records));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                CONSUMER.close();
            }
        }
    }

    @Slf4j
     static class RecordsHandler implements Runnable {

        private final ConsumerRecords<String, String> RECORDS;

        RecordsHandler(ConsumerRecords<String, String> records) {
            this.RECORDS = records;
        }

        @Override
        public void run() {
            KafkaUtil.printMessage(RECORDS);
        }
    }
}
