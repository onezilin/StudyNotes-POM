package com.studynotes.demo06_adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 */
public interface AdapterEntity {

    /**
     * Description: 原始类
     */
    @Slf4j
    class Source {

        public void specificRequest() {
            log.info("This is specificRequest");
        }
    }

    /**
     * Description: 目标抽象类
     */
    abstract class Target {

        public abstract void request();
    }

    /**
     * Description: 适配器类，将原始类功能可以转换为目标类的功能
     */
    class Adapter extends Target {

        private final Source source;

        public Adapter(Source source) {
            this.source = source;
        }

        @Override
        public void request() {
            source.specificRequest();
        }
    }
}
