package com.studynotes.demo07_chain;

import static com.studynotes.demo07_chain.ChainEntity.*;

/**
 * Description: 责任链模式，
 * 有如下特点：
 * 1、链上的每个 Handler 都有机会处理请求
 * 2、链上的每个 Handler 都持有链中的 next Handler
 * 3、链上的每个 Handler 都可以决定是否将请求传递给下一个 Handler
 * <p>
 * 优点：
 * 1、请求者和 Handler 解耦：请求者不需要知道哪个 Handler 来处理请求，Handler 也只需要处理请求，不需要知道由谁传递，两者解耦
 * 2、可以动态的添加或修改 Handler：只需要按照实际需求组装 Handler 即可
 * 3、每个 Handler 只需要关注自己的业务逻辑，不需要关注整个流程
 * <p>
 * 应用：
 * 1、Java 中的异常处理机制
 * 2、Spring 中的 Interceptor 拦截器
 * 3、Servlet 中的 Filter 过滤器
 */
public class Demo01_Chain {

    public static void main(String[] args) {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();

        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(handlerC);

        handlerA.handleRequest("A");
        System.out.println("====================================");
        handlerA.handleRequest("B");
        System.out.println("====================================");
        handlerA.handleRequest("C");
        System.out.println("====================================");
        handlerA.handleRequest("D");
    }
}
