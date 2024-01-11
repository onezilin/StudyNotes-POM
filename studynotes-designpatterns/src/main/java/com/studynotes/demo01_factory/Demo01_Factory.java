package com.studynotes.demo01_factory;

import org.junit.jupiter.api.Test;

import static com.studynotes.demo01_factory.FactoryEntity.*;

/**
 * Description: 简单工厂模式：一个工厂类根据传入的参数决定创建出哪一种产品类的实例。
 * <p>
 * 1. 优点：工厂类包含必要的逻辑判断，根据客户端的选择条件动态实例化相关的类，对于客户端来说，去除了与具体产品的依赖。
 * 2. 缺点：工厂类的职责相对过重，增加新的产品需要修改工厂类的判断逻辑，违背了开闭原则。
 * 3. 使用场景：工厂类负责创建的对象比较少；客户端只知道传入工厂类的参数，对于如何创建对象不关心。
 * 4. 注意事项：工厂类一般使用静态方法，通过静态方法可以直接调用，不需要实例化工厂类。
 */
public class Demo01_Factory {

    @Test
    public void test() {
        ProductFactory productFactory = new ProductFactory();

        Product productA = productFactory.getProduct("ProductA");
        Product productB = productFactory.getProduct("ProductB");
    }

    /**
     * Description: 工厂类
     */
    static class ProductFactory {
        public Product getProduct(String productName) {
            if ("ProductA".equals(productName)) {
                return new ProductA();
            } else if ("ProductB".equals(productName)) {
                return new ProductB();
            } else {
                return null;
            }
        }
    }
}
