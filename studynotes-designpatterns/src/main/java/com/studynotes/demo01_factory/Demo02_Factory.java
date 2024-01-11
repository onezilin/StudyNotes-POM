package com.studynotes.demo01_factory;

import org.junit.jupiter.api.Test;

import static com.studynotes.demo01_factory.FactoryEntity.*;

/**
 * Description: 抽象工厂模式：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 */
public class Demo02_Factory {

    @Test
    public void test() {
        Factory factoryA = new FactoryA();
        Product productA = factoryA.getProduct();

        Factory factoryB = new FactoryB();
        Product productB = factoryB.getProduct();
    }

    /**
     * Description: 工厂接口
     */
    interface Factory {
        Product getProduct();
    }

    static class FactoryA implements Factory {
        Product productA;

        public FactoryA() {
            this.productA = new ProductA();
        }

        @Override
        public Product getProduct() {
            return productA;
        }
    }

    static class FactoryB implements Factory {
        Product productB;

        public FactoryB() {
            this.productB = new ProductB();
        }

        @Override
        public Product getProduct() {
            return productB;
        }
    }
}
