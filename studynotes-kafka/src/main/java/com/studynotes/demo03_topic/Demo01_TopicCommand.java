package com.studynotes.demo03_topic;

import com.studynotes.constant.CommonConstants;
import kafka.admin.TopicCommand;

import static com.studynotes.KafkaUtil.DEFINE_TOPIC_NAME;

/**
 * Description: TopicCommand 通过命令的形式创建、删除、查看主题
 */
public class Demo01_TopicCommand {

    public static void main(String[] args) {
        String[] options = new String[]{"--zookeeper", CommonConstants.ZOOKEEPER_HOST, "--create", "--replication" +
                "-factor", "1", "--partitions", "1", "--topic", DEFINE_TOPIC_NAME};
        TopicCommand.main(options);
    }
}
