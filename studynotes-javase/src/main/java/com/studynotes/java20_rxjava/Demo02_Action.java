package com.studynotes.java20_rxjava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

import static com.studynotes.java20_rxjava.Demo01_Observable.getObservable1;

/**
 * Description:
 * <p>
 * 每次实现 Observer 接口都需要重写 onNext()、onCompleted() 和 onError() 3 个方法，但是我们有时候只想重写其中的 1 个或 2 个方法
 * <p>
 * 因此提供 Action0（提供无参的 call()方法）和 Action1（提供有参的 call()方法）接口（以下统称 Action）
 * 同时 Observable 提供 subscribe() 有几个重载方法，用于接收 Action 类型参数
 * 让我们只需要传入 Action 实现类，专注于指定事件的回调
 */
@Slf4j
public class Demo02_Action {

    @Test
    void test() {
        Observable<String> observable = getObservable1();

        // 只处理 onNext 事件
        Action1<String> onNextAction = new MyAction1<>();
        observable.subscribe(onNextAction);

        // 处理 onNext、onCompleted 事件
        Action1<Throwable> onErrorAction = new MyAction1<>();
        observable.subscribe(onNextAction, onErrorAction);

        // 处理 onNext、onCompleted、onError 事件，和正常的 Observable 实现类一致
        Action0 onCompletedAction = new MyAction0();
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    /**
     * Description: Action0 无参的 call()方法
     */
    class MyAction0 implements Action0 {

        @Override
        public void call() {
            log.info("Action0 call()");
        }
    }

    /**
     * Description: Action1 有参的 call()方法
     */
    static class MyAction1<T> implements Action1<T> {

        @Override
        public void call(T t) {
            log.info("Action1 call()：{}", t);
        }
    }
}
