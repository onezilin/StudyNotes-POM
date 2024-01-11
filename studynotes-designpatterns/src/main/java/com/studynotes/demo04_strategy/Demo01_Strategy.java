package com.studynotes.demo04_strategy;

import org.junit.jupiter.api.Test;

import static com.studynotes.demo04_strategy.StrategyEntity.*;

/**
 * Description: 策略模式：定义了算法族，分别封装起来，让它们之间可以互相替换，在不影响客户端的情况下，动态改变算法
 */
public class Demo01_Strategy {

    @Test
    public void test() {
        Context context = new Context();

        // Context 使用策略A
        ConcreteStrategyA concreteStrategyA = new ConcreteStrategyA();
        context.setStagtegy(concreteStrategyA);

        // 客户端调用算法
        context.algorithm();

        // Context 使用策略B
        ConcreteStrategyB concreteStrategyB = new ConcreteStrategyB();
        context.setStagtegy(concreteStrategyB);

        // 客户端调用算法，但是对底层算法改动无感知
        context.algorithm();
    }

    /**
     * Description: 上下文环境类，向客户端提供算法的调用接口
     */
    static class Context {
        private Strategy strategy;

        public void setStagtegy(Strategy strategy) {
            this.strategy = strategy;
        }

        public void algorithm() {
            strategy.algorithm();
        }
    }
}
