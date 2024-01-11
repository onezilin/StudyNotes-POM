package com.studynotes.demo07_chain;

/**
 * Description:
 */
public interface ChainEntity {

    abstract class Handler {

        private Handler nextHandler;

        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }

        public Handler getNextHandler() {
            return nextHandler;
        }

        /**
         * Description: 触发责任链中的下一个处理器
         *
         * @param request 请求参数
         */
        public void fireNextHandler(String request) {
            if (getNextHandler() != null) {
                getNextHandler().handleRequest(request);
            }
        }

        /**
         * Description: 处理请求，由子类实现
         *
         * @param request 请求参数
         */
        public abstract void handleRequest(String request);
    }

    class HandlerA extends Handler {
        @Override
        public void handleRequest(String request) {
            if ("A".equals(request)) {
                System.out.println("HandlerA 处理请求");
            } else {
                System.out.println("HandlerA 无法处理请求，交给下一个处理器处理");
                fireNextHandler(request);
            }
        }
    }

    class HandlerB extends Handler {
        @Override
        public void handleRequest(String request) {
            if ("B".equals(request)) {
                System.out.println("HandlerB 处理请求");
            } else {
                System.out.println("HandlerB 无法处理请求，交给下一个处理器处理");
                fireNextHandler(request);
            }
        }
    }

    class HandlerC extends Handler {
        @Override
        public void handleRequest(String request) {
            if ("C".equals(request)) {
                System.out.println("HandlerC 处理请求");
            } else {
                System.out.println("HandlerC 无法处理请求，交给下一个处理器处理");
                fireNextHandler(request);
            }
        }
    }
}
