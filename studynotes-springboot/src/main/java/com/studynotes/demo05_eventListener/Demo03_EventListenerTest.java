package com.studynotes.demo05_eventListener;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description: 通过 Spring 提供的 ApplicationEventPublisher 发布事件
 */
@Slf4j
@SpringBootTest
public class Demo03_EventListenerTest {

    // ApplicationContext 继承 ApplicationEventPublisher 接口，提供 publishEvent 发布事件功能
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        Demo01_MyApplicationEvent endEvt = new Demo01_MyApplicationEvent(this, "methodToMonitor");
        this.applicationContext.publishEvent(endEvt);
    }
}

/**
 * Description: 自定义事件监听器，监听 EventPublisher 发布的 EventObject 事件
 */
@Component
class MethodExecutionEventListener implements ApplicationListener<Demo01_MyApplicationEvent> {

    @Override
    public void onApplicationEvent(@NonNull Demo01_MyApplicationEvent evt) {
        // 对监听到的事件进行操作
        System.out.println("监听到事件：" + evt.getMethodName());
    }
}
