package com.studynotes.java20_rxjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.functions.Action1;

import static com.studynotes.java20_rxjava.Demo01_Observable.getObservable1;
import static com.studynotes.java20_rxjava.Demo02_Action.MyAction1;

/**
 * Description: Observable 提供 map、flatMap 方法，用于转换成其他类型
 */
public class Demo03_Map {

    @Test
    void test() {
        Observable<String> observable = getObservable1();
        Action1<MyTest> onNextAction = new MyAction1<>();

        observable.map(MyTest::new).subscribe(onNextAction);
    }

    @Data
    @AllArgsConstructor
    static class MyTest {

        private String s;
    }
}
