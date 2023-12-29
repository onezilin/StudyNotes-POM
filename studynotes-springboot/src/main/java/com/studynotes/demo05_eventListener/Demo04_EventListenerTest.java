package com.studynotes.demo05_eventListener;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

import javax.annotation.Resource;

/**
 * Description: 使用 @EventListener 注解定义事件监听器，处理 EventObject 事件
 */
@Slf4j
@SpringBootTest
public class Demo04_EventListenerTest {

    // ApplicationContext 继承 ApplicationEventPublisher 接口，提供 publishEvent 发布事件功能
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        applicationContext.publishEvent(new Demo01_MyApplicationEvent(this, "eventListenerTest"));
    }

    @EventListener(Demo01_MyApplicationEvent.class)
    public void executeEventListener(Demo01_MyApplicationEvent event) {
        log.info("发布的事件为：{}", event);
    }
}
