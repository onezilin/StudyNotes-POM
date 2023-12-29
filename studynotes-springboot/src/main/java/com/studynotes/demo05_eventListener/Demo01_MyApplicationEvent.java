package com.studynotes.demo05_eventListener;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * Description: 定义事件
 */
@Slf4j
public class Demo01_MyApplicationEvent extends ApplicationEvent {

    @Getter
    private String methodName;

    public Demo01_MyApplicationEvent(Object source, String methodName) {
        super(source);
        log.info("I am ApplicationEvent");
        this.methodName = methodName;
    }
}

