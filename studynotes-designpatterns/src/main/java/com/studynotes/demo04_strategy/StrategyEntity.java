package com.studynotes.demo04_strategy;

/**
 * Description:
 */
public interface StrategyEntity {

    // 算法抽象类
    interface Strategy {
        void algorithm();
    }

    // 算法实现类
    class ConcreteStrategyA implements Strategy {
        @Override
        public void algorithm() {
            System.out.println("使用策略A");
        }
    }

    class ConcreteStrategyB implements Strategy {
        @Override
        public void algorithm() {
            System.out.println("使用策略B");
        }
    }
}
