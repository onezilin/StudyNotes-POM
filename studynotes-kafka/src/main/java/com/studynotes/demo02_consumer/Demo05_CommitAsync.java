package com.studynotes.demo02_consumer;

import com.studynotes.KafkaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;

import java.time.Duration;

/**
 * Description: Consumer 提供 commitAsync() 方法异步手动提交偏移量，提交成功后会回调 Callback 接口
 */
@Slf4j
public class Demo05_CommitAsync {

    @Test
    public void commitAsync1() {
        KafkaConsumer<String, String> consumer = Demo04_CommitSync.getConsumer();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            KafkaUtil.printMessage(records);

            consumer.commitAsync((offsets, exception) -> {
                if (exception == null) {
                    log.info("提交偏移量 {}", offsets);
                } else {
                    log.error("fail to commit offset {}, exception {}", offsets, exception);
                }
            });
        }
    }
}
