package com.studynotes.demo03_prototype;

import lombok.Getter;
import org.junit.jupiter.api.Test;

/**
 * Description: 原型模式：用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 */
public class Demo01_Prototype {

    @Test
    public void test() {
        A a = new A();
        System.out.println("a：" + a);
        Prototype cp = new ConcretePrototype(a);
        for (int i = 0; i < 5; i++) {
            ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
            clonecp.show();
        }
    }

    interface Prototype {

        Prototype clone();
    }

    static class ConcretePrototype implements Prototype, Cloneable {

        @Getter
        private final A a;

        public ConcretePrototype(A a) {
            this.a = a;
        }

        public ConcretePrototype clone() {
            ConcretePrototype prototype = null;
            try {
                prototype = (ConcretePrototype) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return prototype;
        }

        public void show() {
            System.out.println("ConcretePrototype:{}" + this + "a:{}" + a);
        }
    }

    /**
     * Description: 用于证明 Object.clone 只是浅拷贝，会发现 clone 后，ConcretePrototype 中 都是同一个 a
     */
    static class A {
    }
}
