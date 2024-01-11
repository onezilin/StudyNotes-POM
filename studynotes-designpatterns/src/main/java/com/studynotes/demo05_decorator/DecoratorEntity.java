package com.studynotes.demo05_decorator;

import static com.studynotes.demo05_decorator.BatterCakeEntity.BatterCake;

/**
 * Description: 装饰者
 */
public interface DecoratorEntity {

    /**
     * Description: 抽象装饰者
     */
    abstract class AbstractDecorator implements BatterCake {

        private final BatterCake batterCake;

        public AbstractDecorator(BatterCake batterCake) {
            this.batterCake = batterCake;
        }

        @Override
        public String getDesc() {
            return batterCake.getDesc();
        }

        @Override
        public int cost() {
            return batterCake.cost();
        }
    }

    /**
     * Description: 加鸡蛋功能的装饰者
     */
    class EggDecorator extends AbstractDecorator {

        public EggDecorator(BatterCake batterCake) {
            super(batterCake);
        }

        @Override
        public String getDesc() {
            return super.getDesc() + " 加一个鸡蛋";
        }

        @Override
        public int cost() {
            return super.cost() + 1;
        }
    }

    /**
     * Description: 加香肠功能的装饰者
     */
    class SausageDecorator extends AbstractDecorator {
        public SausageDecorator(BatterCake batterCake) {
            super(batterCake);
        }

        @Override
        public String getDesc() {
            return super.getDesc() + " 加一根香肠";
        }

        @Override
        public int cost() {
            return super.cost() + 2;
        }
    }
}
