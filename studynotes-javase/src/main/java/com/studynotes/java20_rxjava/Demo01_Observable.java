package com.studynotes.java20_rxjava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Description: 测试 RxJava 提供的 Observer 观察者模式
 * <p>
 * 其中有三个角色：
 * * Observer：观察者
 * * Observable：被观察者
 * * subscribe：观察者注册到被观察者上，当被观察者产生事件时（例如：属性修改），会通知观察者
 */
@Slf4j
public class Demo01_Observable {

    @Test
    void test() {
        Observer<String> observer = new MyObserver();
        Observable<String> observable = getObservable1();
        // 注册
        observable.subscribe(observer);
    }

    /**
     * Description: 构建 Observable 被观察者
     */
    static Observable<String> getObservable1() {
        log.info("创建被观察者");
        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                // 触发观察者的 onNext() 回调
                log.info("查看 Observable 产生数据时所在线程");
                subscriber.onNext("Hello");
                subscriber.onNext("Wrold");

                subscriber.onCompleted();
            }
        });
    }

    /**
     * Description: 其他的构建方式
     */
    Observable<String> getObservable2() {
        return Observable.just("Hello", "World");
    }

    Observable<String> getObservable3() {
        String[] words = {"Hello", "World"};
        return Observable.from(words);
    }
}
