package com.studynotes.demo05_decorator;

/**
 * Description: 被装饰者
 */
public interface BatterCakeEntity {

    /**
     * Description: 被装饰者接口
     */
    interface BatterCake {

        String getDesc();

        int cost();
    }

    /**
     * Description: 被装饰者实现类
     */
    class MyBatterCake implements BatterCake {

        @Override
        public String getDesc() {
            return "煎饼";
        }

        @Override
        public int cost() {
            return 8;
        }
    }
}
