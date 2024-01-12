package com.studynotes.demo03_topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static com.studynotes.KafkaUtil.DEFINE_TOPIC_NAME;

/**
 * Description: 获取 topic 的 describe
 */
@Slf4j
public class Demo03_DescribeTopic {

    public static void main(String[] args) {
        AdminClient client = Demo02_KafkaAdminClient.client;
        DescribeTopicsResult topicsResult =
                client.describeTopics(Collections.singleton(DEFINE_TOPIC_NAME));
        try {
            TopicDescription topicDescription = topicsResult.all().get().get(DEFINE_TOPIC_NAME);
            log.info("获取topic的describe: {}", topicDescription);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
