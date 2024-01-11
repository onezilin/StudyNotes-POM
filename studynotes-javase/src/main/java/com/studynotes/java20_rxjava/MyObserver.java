package com.studynotes.java20_rxjava;

import lombok.extern.slf4j.Slf4j;
import rx.Observer;

/**
 * Description: 自定义 Observer 观察者
 */
@Slf4j
public class MyObserver implements Observer<String> {

    /**
     * Description: 结束事件回调，用于表示结束的标志
     */
    @Override
    public void onCompleted() {
        log.info("Completed");
    }

    /**
     * Description: 异常事件回调，当事件处理过程中出异常时的回调，和 onCompleted 之间同时只会有一个执行
     */
    @Override
    public void onError(Throwable e) {
        log.info("Error");
    }

    /**
     * Description: 事件产生后的回调
     */
    @Override
    public void onNext(String s) {
        log.info("获取值：{}", s);
    }

}
