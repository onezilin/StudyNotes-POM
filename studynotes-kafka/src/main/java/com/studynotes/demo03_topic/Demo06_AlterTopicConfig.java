package com.studynotes.demo03_topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AlterConfigOp;
import org.apache.kafka.clients.admin.AlterConfigsResult;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.studynotes.KafkaUtil.DEFINE_TOPIC_NAME;

/**
 * Description: 修改 Topic 配置
 */
public class Demo06_AlterTopicConfig {

    public static void main(String[] args) {
        AdminClient client = Demo02_KafkaAdminClient.client;
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, DEFINE_TOPIC_NAME);

        ConfigEntry entry = new ConfigEntry("cleanup.policy", "compact");
        // 注意：OpType.APPEND是为配置值是列表类型时有用
        AlterConfigOp alterConfigOp = new AlterConfigOp(entry, AlterConfigOp.OpType.SET);
        // 每个topic设置对应的配置
        Map<ConfigResource, Collection<AlterConfigOp>> configs = new HashMap<>();
        configs.put(resource, Collections.singleton(alterConfigOp));
        // 修改配置
        AlterConfigsResult result = client.incrementalAlterConfigs(configs);
        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
