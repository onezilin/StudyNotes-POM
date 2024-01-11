package com.studynotes.demo08_pubAndSub;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 */
public interface PubAndSubEntity {

    /**
     * Description: 发布者
     */
    abstract class Subject {

        // 存储所有注册的订阅者
        protected List<Observe> observes = new ArrayList<>();
        // 观察者状态
        int subjectState;

        public int getSubjectState() {
            return this.subjectState;
        }

        public void setSubjectState(int subjectState) {
            this.subjectState = subjectState;
        }

        // 订阅者订阅
        public void attach(Observe observe) {
            observes.add(observe);
        }

        // 订阅者取消订阅
        public void detch(Observe observe) {
            observes.remove(observe);
        }

        // 声明抽象通知方法，用来通知订阅者
        public abstract void notifyObserve();
    }

    class ConcreteSubject extends Subject {
        @Override
        public void notifyObserve() {
            // 遍历订阅者，调用每个订阅者的响应方法，参数是发布者最新的状态
            this.observes.forEach(observe -> observe.update(this));
        }
    }

    /**
     * Description: 订阅者
     */
    interface Observe {
        void update(Subject subject);
    }

    // 具体订阅者
    class ConcreteObserve implements Observe {
        String name;
        int observeState;

        ConcreteObserve(String name) {
            this.name = name;
        }

        @Override
        public void update(Subject subject) {
            observeState = subject.subjectState;
            System.out.println("订阅者姓名: " + name + ", 随着发布者状态变动的订阅者状态: " + observeState);
        }
    }
}
