package com.studynotes.java04_interface;

import org.junit.jupiter.api.Test;

/**
 * Description: 接口最经典的用法就是工厂方法设计模式
 */
public class Demo11_InterfaceFactory {

    @Test
    void test() {
        serviceConsumer(new Service1Factory());
        serviceConsumer(new Service2Factory());
    }

    public static void serviceConsumer(ServiceFactory fact) {
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    interface Service {
        void method1();

        void method2();
    }

    interface ServiceFactory {
        Service getService();
    }

    class Service1 implements Service {
        Service1() {
        } // Package access

        @Override
        public void method1() {
            System.out.println("Service1 method1");
        }

        @Override
        public void method2() {
            System.out.println("Service1 method2");
        }
    }

    class Service1Factory implements ServiceFactory {
        @Override
        public Service getService() {
            return new Service1();
        }
    }

    class Service2 implements Service {
        Service2() {
        } // Package access

        @Override
        public void method1() {
            System.out.println("Service2 method1");
        }

        @Override
        public void method2() {
            System.out.println("Service2 method2");
        }
    }

    class Service2Factory implements ServiceFactory {
        @Override
        public Service getService() {
            return new Service2();
        }
    }
}
