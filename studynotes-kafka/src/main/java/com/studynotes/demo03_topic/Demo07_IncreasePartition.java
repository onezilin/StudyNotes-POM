package com.studynotes.demo03_topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreatePartitionsResult;
import org.apache.kafka.clients.admin.NewPartitions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.studynotes.KafkaUtil.DEFINE_TOPIC_NAME;

/**
 * Description: 添加主题分区数
 */
public class Demo07_IncreasePartition {
    public static void main(String[] args) {
        AdminClient client = Demo02_KafkaAdminClient.client;

        // 添加主题分区为5
        NewPartitions newPartitions = NewPartitions.increaseTo(5);
        // 每个topic设置对应的分区
        Map<String, NewPartitions> newPartitionsMap = new HashMap<>();
        newPartitionsMap.put(DEFINE_TOPIC_NAME, newPartitions);

        CreatePartitionsResult result = client.createPartitions(newPartitionsMap);
        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
