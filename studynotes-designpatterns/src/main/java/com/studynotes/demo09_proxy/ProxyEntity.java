package com.studynotes.demo09_proxy;

/**
 * Description:
 */
public interface ProxyEntity {

    /**
     * Description: 被代理类
     */
    abstract class Subject {
        public abstract void request();
    }

    class RealSubject extends Subject {
        public void request() {
            System.out.println("request");
        }
    }

    /**
     * Description: 代理类
     */
    class Proxy extends Subject {

        private final Subject subject = new RealSubject();

        // 访问被代理类前处理
        public void preRequest() {
            System.out.println("Proxy.preRequest()");
        }

        public void request() {
            preRequest();
            subject.request();
            afterRequest();
        }

        // 访问被代理类后处理
        public void afterRequest() {
            System.out.println("Proxy.afterRequest()");
        }
    }
}
