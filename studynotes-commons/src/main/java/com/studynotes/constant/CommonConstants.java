package com.studynotes.constant;

/**
 * Description: 常量类
 */
public interface CommonConstants {

    String HOST = "localhost";

    int PORT = 1234;

    String LINUX_HOST = "192.168.190.134";

    String ZOOKEEPER_HOST = LINUX_HOST + ":2181" + "," + LINUX_HOST + ":2182" + "," + LINUX_HOST + ":2183";
}
