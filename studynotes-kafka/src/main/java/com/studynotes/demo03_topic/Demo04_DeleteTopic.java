package com.studynotes.demo03_topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteTopicsResult;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static com.studynotes.KafkaUtil.DEFINE_TOPIC_NAME;

/**
 * Description: 删除 topic
 */
@Slf4j
public class Demo04_DeleteTopic {

    public static void main(String[] args) {
        AdminClient client = Demo02_KafkaAdminClient.client;
        DeleteTopicsResult topicsResult =
                client.deleteTopics(Collections.singleton(DEFINE_TOPIC_NAME));
        try {
            topicsResult.all().get();
            log.info("删除topic");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
