package com.studynotes.constant;

/**
 * Description: 常量类
 */
public interface CommonConstants {

    String HOST = "localhost";

    int PORT = 1234;

    String LINUX_HOST = "192.168.190.134";

    String ZOOKEEPER_HOST = LINUX_HOST + ":2181" + "," + LINUX_HOST + ":2182" + "," + LINUX_HOST + ":2183";

    int REDIS_PORT = 6379;

    int[] REDIS_CLUSTER_PORTS = {6379, 6380, 6381, 6382, 6383, 6384};

    String KAFKA_BROKER_LIST = LINUX_HOST + ":9092" + "," + LINUX_HOST + ":9093" + "," + LINUX_HOST + ":9094";
}
