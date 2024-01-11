package com.studynotes.demo05_decorator;

import org.junit.jupiter.api.Test;

import static com.studynotes.demo05_decorator.BatterCakeEntity.BatterCake;
import static com.studynotes.demo05_decorator.BatterCakeEntity.MyBatterCake;
import static com.studynotes.demo05_decorator.DecoratorEntity.EggDecorator;
import static com.studynotes.demo05_decorator.DecoratorEntity.SausageDecorator;

/**
 * Description: 装饰者模式：动态的将新功能附加到对象上。在对象功能扩展方面，它比继承更有弹性，装饰者模式也体现了开闭原则（OCP）
 */
public class Demo01_Decorator {

    @Test
    public void test() {
        BatterCake batterCake = new MyBatterCake();

        // 1、第一次使用装饰者增强一个煎饼，如果继承的话要重新继承一个类去增强煎饼
        batterCake = new EggDecorator(batterCake);

        // 2、第二次装饰者又使用同样的手法去增强一个煎饼，如果继承的话要重新继承一个类去增强煎饼
        batterCake = new EggDecorator(batterCake);

        // 3、以此类推
        batterCake = new SausageDecorator(batterCake);

        // 煎饼 加一个鸡蛋 加一个鸡蛋 加一根香肠 销售价格:12
        System.out.println(batterCake.getDesc() + " 销售价格:" + batterCake.cost());
    }
}






