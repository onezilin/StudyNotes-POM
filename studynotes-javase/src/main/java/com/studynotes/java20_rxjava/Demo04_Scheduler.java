package com.studynotes.java20_rxjava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.studynotes.java20_rxjava.Demo01_Observable.getObservable1;
import static com.studynotes.java20_rxjava.Demo02_Action.MyAction1;
import static com.studynotes.java20_rxjava.Demo03_Map.MyTest;

/**
 * Description:
 * <p>
 * 前面的例子可以看出，Observable 产生事件，触发注册的 Observer 的回调，但是回调函数是由当前线程执行的，而观察者模式本应该是后台（异步）执行
 * <p>
 * 因此提供：
 * * subscribeOn()方法，用于指定 Observable 发起时所在的线程;
 * * observeOn()方法，用于指定 Observer 回调所在的线程
 */
@Slf4j
public class Demo04_Scheduler {

    @Test
    void test() {
        Observer<String> observer = new MyObserver();
        Observable<String> observable = getObservable1();
        Action1<MyTest> onNextAction = new MyAction1<>();

        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .map(s -> {
                    MyTest o = new MyTest(s);
                    log.info("第一次 observeOn，查看当前所在线程");
                    return o;
                })
                .observeOn(Schedulers.newThread())
                .map(o -> {
                    log.info("第二次 observeOn，查看当前所在线程");
                    return o.getS();
                })
                .subscribe(observer);
    }
}
