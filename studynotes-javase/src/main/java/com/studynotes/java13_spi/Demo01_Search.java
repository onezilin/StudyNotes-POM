package com.studynotes.java13_spi;

/**
 * Description:
 * 测试 SPI（Service Provider Interface） 机制，SPI 是 JDK 内置的一种服务提供发现机制，可以用于实现插件机制。
 */
public interface Demo01_Search {
    void searchDoc(String keyword);
}
