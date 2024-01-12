package com.studynotes.demo02_consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.PartitionInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.studynotes.KafkaUtil.DEFAULT_CONSUMER;
import static com.studynotes.KafkaUtil.DEFAULT_TOPIC_NAME;

/**
 * Description: Consumer 的 partitionsFor() 方法可以查询指定主题的分区信息
 */
@Slf4j
public class Demo02_PartitionsFor {

    @Test
    public void test() {
        List<PartitionInfo> partitionInfoList = DEFAULT_CONSUMER.partitionsFor(DEFAULT_TOPIC_NAME);
        // 打印分区信息
        partitionInfoList.forEach(partitionInfo -> {
            log.info("partitionInfo: topic -> {}, partition -> {}", partitionInfo.topic(), partitionInfo.partition());
        });
    }
}
