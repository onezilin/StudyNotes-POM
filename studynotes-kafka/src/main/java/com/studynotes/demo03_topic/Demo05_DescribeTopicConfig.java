package com.studynotes.demo03_topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static com.studynotes.KafkaUtil.DEFINE_TOPIC_NAME;

/**
 * Description: 通过 AdminClient 获取 topic 的配置信息
 */
@Slf4j
public class Demo05_DescribeTopicConfig {

    public static void main(String[] args) {
        AdminClient client = Demo02_KafkaAdminClient.client;
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, DEFINE_TOPIC_NAME);

        DescribeConfigsResult result = client.describeConfigs(Collections.singletonList(resource));
        try {
            Config config = result.all().get().get(resource);
            log.info("通过KafkaAdminClient获取配置: {}", config);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
