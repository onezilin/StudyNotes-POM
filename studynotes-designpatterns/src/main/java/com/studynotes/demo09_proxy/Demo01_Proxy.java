package com.studynotes.demo09_proxy;

import org.junit.jupiter.api.Test;

import static com.studynotes.demo09_proxy.ProxyEntity.Proxy;
import static com.studynotes.demo09_proxy.ProxyEntity.Subject;

/**
 * Description: 代理模式：为其他对象提供一种代理以控制对这个对象的访问，并且可以在不改变原有对象的情况下，增强原有对象的功能
 */
public class Demo01_Proxy {

    @Test
    public void test() {
        Subject proxy = new Proxy();
        proxy.request();
    }
}
