package com.studynotes.demo01_factory;

/**
 * Description:
 */
public interface FactoryEntity {

    /**
     * Description: 产品接口
     */
    interface Product {
    }

    /**
     * Description: 具体产品 ProductA
     */
    class ProductA implements Product {
    }

    /**
     * Description: 具体产品 ProductB
     */
    class ProductB implements Product {
    }
}
