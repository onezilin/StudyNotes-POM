package com.studynotes.demo03_topic;

import com.studynotes.constant.CommonConstants;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.studynotes.KafkaUtil.DEFINE_TOPIC_NAME;

/**
 * Description: 使用 KafkaAdminClient 替代 TopicCommand 创建topic
 */
public class Demo02_KafkaAdminClient {

    private static final int partitionNum = 4;
    private static final short replicasNum = 1;

    public static AdminClient client;

    static {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConstants.KAFKA_BROKER_LIST);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        // 创建KafkaAdminClien实例，连接到Kafka集群
        client = AdminClient.create(properties);
    }

    public static void main(String[] args) {
        // 第一种方式：直接设置主题信息
        // NewTopic newTopic = new NewTopic(createTopicName, partitionNum, replicasNum);

        // 第二种方式：设置主题信息，自定义分区所在broker
        Map<Integer, List<Integer>> replicasAssignments = new HashMap<>();
        replicasAssignments.put(0, Arrays.asList(0, 1));
        replicasAssignments.put(1, Arrays.asList(0, 1));
        replicasAssignments.put(2, Arrays.asList(1, 0));
        replicasAssignments.put(3, Arrays.asList(1, 0));
        NewTopic newTopic = new NewTopic(DEFINE_TOPIC_NAME, replicasAssignments);

        // 添加自定义配置
        Map<String, String> myConfig = new HashMap<>();
        myConfig.put("cleanup.policy", "compact");
        newTopic.configs(myConfig);
        // 创建主题
        CreateTopicsResult result = client.createTopics(Collections.singleton(newTopic));

        try {
            // 获取所有创建 Topic 的异步结果
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }

    }
}
