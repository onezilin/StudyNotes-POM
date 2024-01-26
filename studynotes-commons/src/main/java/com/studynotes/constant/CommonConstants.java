package com.studynotes.constant;

/**
 * Description: 常量类
 */
public interface CommonConstants {

    // 默认主机和端口
    String HOST = "localhost";
    int PORT = 1234;

    // Linux 主机
    String LINUX_HOST = "192.168.190.134";

    // Zookeeper 集群地址
    String ZOOKEEPER_HOST = LINUX_HOST + ":2181" + "," + LINUX_HOST + ":2182" + "," + LINUX_HOST + ":2183";

    // Redis 集群端口
    int[] REDIS_CLUSTER_PORTS = {6379, 6380, 6381, 6382, 6383, 6384};

    // Kafka 集群地址
    String KAFKA_BROKER_LIST = LINUX_HOST + ":9092" + "," + LINUX_HOST + ":9093" + "," + LINUX_HOST + ":9094";

    // MySQL 配置信息
    String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    String MYSQL_URL = "jdbc:mysql://" + LINUX_HOST + ":3306/db2019?useUnicode=true&characterEncoding=utf8&useSSL=false";
    String MYSQL_USERNAME = "root";
    String MySQL_PASSWORD = "W110514";
}
