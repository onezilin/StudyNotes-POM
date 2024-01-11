package com.studynotes.demo08_pubAndSub;

import static com.studynotes.demo08_pubAndSub.PubAndSubEntity.*;

/**
 * Description: 观察者模式（发布订阅模式）：发布者和订阅者之间是松耦合的，发布者不知道订阅者的存在，订阅者也不知道发布者的存在，它们通过消息代理进行通信
 */
public class Demo01_PublishAndSubscribe {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observe objA = new ConcreteObserve("A");
        Observe objB = new ConcreteObserve("B");

        // 订阅注册
        subject.attach(objA);
        subject.attach(objB);

        subject.setSubjectState(1);
        // 发布消息
        subject.notifyObserve();

        subject.detch(objB);
        subject.setSubjectState(2);
        // 发布消息
        subject.notifyObserve();

    }
}
